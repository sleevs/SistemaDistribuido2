






variable "kafka_version" {
  description = "Apache Kafka Version"
  type        = string
  default     = "4.1.2"
}

variable "cluster_id" {

  description = "KRaft id shared between 3 nodes"
  type        = string
  sensitive   = true
}

variable "kali1_ip" {
  type    = string
  default = "IP_VM_LINUX_1"
}



variable "kali2_ip" {
  type    = string
  default = "IP_VM_LINUX_2"
}



variable "windows_ip" {
  type    = string
  default = "IP_VM_WINDOWS"
}


variable "ssh_user" {
  description = "SSH remote User"
  type        = string
  default     = "jsn"
}


variable "ssh_private_key" {
  description = "private SSH path key"
  type        = string
  default     = "~/.ssh/kafka_lab"
}


# public chave ssh
# ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIJG5ZlFTFgIlDNS338vcyl6r6xP/H6jWADoeybwmhaJa terraform-kafka



