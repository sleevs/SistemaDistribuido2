






terraform {

  required_version = ">= 1.5.0"

  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}


#docekr local pc 1

provider "docker" {
  alias = "kali1"
  host  = "unix:///var/run/docker.sock"
}



#docker local pc 2

provider "docker" {
  alias = "windows"
  host  = "ssh://${var.ssh_user}@${var.windows_ip}:22"

  ssh_opts = [
    "-i", pathexpand("~/.ssh/id_ed25519"),
    "-o", "BatchMode=yes",
    "-o", "IdentitiesOnly=yes",
    "-o", "StrictHostKeyChecking=accept-new"
  ]

}




#docker local pc 3

provider "docker" {
  alias = "kali2"
  host  = "ssh://${var.ssh_user}@${var.kali2_ip}:22"

  ssh_opts = [
    "-i", pathexpand("~/.ssh/id_ed25519"),
    "-o", "BatchMode=yes",
    "-o", "IdentitiesOnly=yes",
    "-o", "StrictHostKeyChecking=accept-new"
  ]
}


