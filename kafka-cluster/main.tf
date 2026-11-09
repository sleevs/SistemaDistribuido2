




locals {

  kafka_cluster_name = "kafka-lab"

  controller_voters = join(",", [
    "1@${var.windows_ip}:9093",
    "2@${var.kali1_ip}:9093",
    "3@${var.kali2_ip}:9093"

  ])


  common_env = [
    "CLUSTER_ID=${var.cluster_id}",
    "KAFKA_PROCESS_ROLES=broker,controller",
    "KAFKA_CONTROLLER_QUORUM_VOTERS=${local.controller_voters}",
    "KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093",
    "KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT",
    "KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER",
    "KAFKA_INNER_BROKER_LISTENER_NAME=PLAINTEXT",
    "KAFKA_LOG_DIRS=/var/lib/kafka/data",
    "KAKFA_DEFAULT_REPLICATION_FACTOR=3",
    "KAFKA_MIN_INSYNC_REPLICAS=2",
    "KAFAK_OFFSETS_TOPIC_REPLICATION_FACTOR=3",
    "KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=2",
    "KAFKA_NUM_PARTITIONS=6",
    "KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0",
    "KAFKA_AUTO_CREATE_TOPICS_ENABLE=false"
  ]

}





#NODE 1 WINDOWS 1
##################################

#NODE 1 WINDOWS 1
##################################

# Changed resource name from "windows" to "kafka1"
resource "docker_image" "kafka1" {
  provider     = docker.windows
  name         = "apache/kafka:${var.kafka_version}"
  keep_locally = true
}

# Changed resource name from "windows_data" to "kafka1_data"
resource "docker_volume" "kafka1_data" {
  provider = docker.windows
  name     = "windows_data"

  lifecycle {
    prevent_destroy = true
  }
}

resource "docker_container" "kafka1" {
  provider = docker.windows
  name     = "kafka1"
  image    = docker_image.kafka1.image_id
  restart  = "unless-stopped"

  env = concat(local.common_env, [
    "KAFKA_NODE_ID=1",
    "KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://${var.windows_ip}:9092"
  ])

  ports {
    internal = 9092
    external = 9092
    ip       = "0.0.0.0"
  }

  ports {
    internal = 9093
    external = 9093
    ip       = "0.0.0.0"
  }

  volumes {
    volume_name    = docker_volume.kafka1_data.name
    container_path = "/var/lib/kafka/data"
  }
}







#NODE 2 KALI LINUX 1
##################################


resource "docker_image" "kafka2" {
  provider     = docker.kali1
  name         = "apache/kafka:${var.kafka_version}"
  keep_locally = true

}

resource "docker_volume" "kafka2_data" {
  provider = docker.kali1
  name     = "kafka2_data"

  lifecycle {
    prevent_destroy = true
  }

}


resource "docker_container" "kafka2" {
  provider = docker.kali1
  name     = "kafka-2"
  image    = docker_image.kafka2.image_id
  restart  = "unless-stopped"

  env = concat(local.common_env, [
    "KAFKA_NODE_ID=2",
    "KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://${var.kali1_ip}:9092"
  ])


  ports {
    internal = 9092
    external = 9092
    ip       = "0.0.0.0"
  }


  ports {
    internal = 9093
    external = 9093
    ip       = "0.0.0.0"
  }

  volumes {
    volume_name    = docker_volume.kafka2_data.name
    container_path = "/var/lib/kafka/data"
  }



}











#NODE 3 KALI LINUX 2
##################################



resource "docker_image" "kafka3" {
  provider     = docker.kali2
  name         = "apache/kafka:${var.kafka_version}"
  keep_locally = true

}

resource "docker_volume" "kafka3_data" {
  provider = docker.kali2
  name     = "kafka3_data"

  lifecycle {
    prevent_destroy = true
  }

}


resource "docker_container" "kafka3" {
  provider = docker.kali2
  name     = "kafka-3"
  image    = docker_image.kafka3.image_id
  restart  = "unless-stopped"

  env = concat(local.common_env, [
    "KAFKA_NODE_ID=3",
    "KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://${var.kali2_ip}:9092"
  ])





  ports {
    internal = 9092
    external = 9092
    ip       = "0.0.0.0"
  }


  ports {
    internal = 9093
    external = 9093
    ip       = "0.0.0.0"
  }

  volumes {
    volume_name    = docker_volume.kafka3_data.name
    container_path = "/var/lib/kafka/data"
  }




}






