#!/usr/bin/env bash

aws_pool_id=$1
aws_region=`cut -d'_' -f1 <<<"$aws_pool_id"`

printf "Starting generate config"

sed \
  -e "s;%AWS_REGION%;$aws_region;g" \
  -e "s;%AWS_POOL_ID%;$aws_pool_id;g" \
  ./../src/main/resources/application.yml.SAMPLE > ./../src/main/resources/application.yml

printf "Successfully created config file 'application.yml'"