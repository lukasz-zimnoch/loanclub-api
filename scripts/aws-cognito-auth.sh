#!/usr/bin/env bash

username=$1
password=$2
client_id=$3

echo "Starting authentication"

access_token=`aws cognito-idp initiate-auth \
  --auth-flow USER_PASSWORD_AUTH \
  --auth-parameters=USERNAME=$username,PASSWORD=$password \
  --client-id $client_id \
  --query AuthenticationResult.AccessToken \
  --output text`

echo "Access token: $access_token"