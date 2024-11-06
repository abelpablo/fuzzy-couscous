provider "hcloud" {
  token = "${var.hcloud_token}"
}


terraform {
  required_providers {
    hcloud = {
      source = "hetznercloud/hcloud"
    }
  }
  required_version = ">= 1.0"
}

resource "hcloud_ssh_key" "default" {
  name       = "Terraform"
  public_key = file("~/.ssh/id_ed25519.pub") #Pablo
}

resource "hcloud_server" "web" {
  name        = "BeerApp" #Pablo
  ssh_keys    = ["Terraform"]
  image       = "ubuntu-22.04"
  server_type = "cx22"
}

# Output the instance's main IP address after provisioning
output "instance_ip" {
  value = hcloud_server.web.ipv4_address
}

resource "null_resource" "dokku_install_and_setup_firewall" {

  connection {
    type        = "ssh"
    user        = "root"
    private_key = file("~/.ssh/id_ed25519")
    host        = hcloud_server.web.ipv4_address
  }

  # Provision Dokku installation after the instance is created
  provisioner "remote-exec" {
    inline = [
      "wget -NP . https://dokku.com/install/master/bootstrap.sh",
      "sudo DOKKU_TAG=v0.34.9 bash bootstrap.sh"
    ]
  }

  # Upload public key to the server
  provisioner "file" {
    source      = "~/.ssh/id_ed25519.pub" #Pablo
    destination = "/root/id_ed25519.pub"  # Save it to a known location on the server
  }

  # Run commands on the server to add the public key to Dokku #Pablo
  provisioner "remote-exec" {
    inline = [
      "echo $(cat /root/id_ed25519.pub) | dokku ssh-keys:add admin",  # Add the key to Dokku from the uploaded file
      "rm /root/id_ed25519.pub"  # Clean up by removing the file after use
    ]
  }

  # Configure Firewall
  provisioner "remote-exec" {
    inline = [
      "ufw allow 80/tcp",   # Allow HTTP traffic
      "ufw reload"          # Reload UFW to apply changes
    ]
  }
  # Upload environment variables file to the instance
  provisioner "file" {
    source = "./.env"  # Path to your local .env.prod file
    destination = "/tmp/.env"  # Destination path on the instanc
  }

# Configure Dokku and set environment variables
  provisioner "remote-exec" {
    inline = [
      "export $(grep -v '^#' /tmp/.env | xargs) && dokku config:set beerapp $(grep -v '^#' /tmp/.env | xargs)",
      "dokku apps:create beerapp",
      "dokku ports:add beerapp http:80:8080",
      "dokku domains:remove-global beerapp",
      "dokku domains:remove beerapp beerapp.beerapp",
      "dokku domains:add beerapp ${hcloud_server.web.ipv4_address}",
      "dokku domains:add-global ${hcloud_server.web.ipv4_address}",
      "dokku plugin:install https://github.com/dokku/dokku-postgres.git",
      "dokku postgres:create beerappdb",
      "dokku postgres:link beerappdb beerapp"

    ]
  }

}

/*resource "null_resource" "deploy_app" { #Pablo
  provisioner "local-exec" {
    command = <<EOT
      git remote add dokku dokku@${hcloud_server.web.ipv4_address}:BeerApp || true && \
      GIT_SSH_COMMAND='ssh -i ~/.ssh/XXXXX -o StrictHostKeyChecking=no' git push dokku main 2>&1 | tee /tmp/git_push.log
    EOT
  }
}*/