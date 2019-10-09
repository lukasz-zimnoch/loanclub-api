#!/usr/bin/env bash

aws_pool_id=$1
aws_region=`cut -d'_' -f1 <<<"$aws_pool_id"`
script_path="$( cd "$(dirname "$0")" ; pwd -P )"

echo "Starting generate config"

sed \
  -e "s;%AWS_REGION%;$aws_region;g" \
  -e "s;%AWS_POOL_ID%;$aws_pool_id;g" \
  $script_path/../../src/main/resources/application.yml.SAMPLE > $script_path/../../src/main/resources/application.yml

echo "Successfully created config file 'application.yml'"