#!/bin/bash
# wait-for-rabbitmq.sh

set -e

host="$1"
port="$2"
eureka_host="eureka-server"
eureka_port="$3"
cmd="$4"

until (echo > /dev/tcp/$host/$port) >/dev/null 2>&1; do
  echo "Waiting for RabbitMQ ($host:$port) to start..."
  sleep 1
done
echo "RabbitMQ is up - executing command"


# Wait for Eureka
until (echo > /dev/tcp/$eureka_host/$eureka_port) >/dev/null 2>&1; do
  echo "Waiting for Eureka ($eureka_host:$eureka_port) to start..."
  sleep 1
done

echo "Eureka is up - executing command"
exec $cmd