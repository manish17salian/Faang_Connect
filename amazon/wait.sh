#!/bin/bash
# wait-for-services.sh

set -e

rabbitmq_host="$1"
rabbitmq_port="$2"
eureka_host="eureka-server"
eureka_port="$3"
cmd="$4"

# Wait for RabbitMQ
until (echo > /dev/tcp/$rabbitmq_host/$rabbitmq_port) >/dev/null 2>&1; do
  echo "Waiting for RabbitMQ ($rabbitmq_host:$rabbitmq_port) to start..."
  sleep 1
done

echo "RabbitMQ is up."

# Wait for Eureka
until (echo > /dev/tcp/$eureka_host/$eureka_port) >/dev/null 2>&1; do
  echo "Waiting for Eureka ($eureka_host:$eureka_port) to start..."
  sleep 1
done

echo "Eureka is up - executing command"
exec $cmd
