#!/bin/bash
# wait-for-rabbitmq.sh

set -e

host="$1"
port="$2"
cmd="$3"

until (echo > /dev/tcp/$host/$port) >/dev/null 2>&1; do
  echo "Waiting for RabbitMQ ($host:$port) to start..."
  sleep 1
done

echo "RabbitMQ is up - executing command"
exec $cmd
