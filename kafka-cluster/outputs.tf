


output "kafka_bootstrap_servers" {
  description = "kafka client connections addresses"

  value = join(",", [
    "${var.windows_ip}:9092",
    "${var.kali1_ip}:9092",
    "${var.kali2_ip}:9092"
  ])
}


output "kafka_controller_quorum" {
  value = local.controller_voters
}


output "kafka_nodes" {
  value = {
    windows = "${var.windows_ip}:9092"
    kali1   = "${var.kali1_ip}:9092"
    kali2   = "${var.kali2_ip}:9092"
  }
}
