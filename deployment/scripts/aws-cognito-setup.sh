#!/usr/bin/env bash

echo "Step 1/7: creating new user pool"
pool_id=`aws cognito-idp create-user-pool \
  --query UserPool.Id \
  --output text \
  --cli-input-json \
    '{
        "PoolName": "loanclub",
        "AutoVerifiedAttributes": ["email"],
        "UsernameAttributes": ["email"],
        "UserPoolTags": {
          "Project": "loanclub"
        },
        "Schema": [
          {
            "Name": "email",
            "StringAttributeConstraints": {
                "MinLength": "0",
                "MaxLength": "2048"
            },
            "DeveloperOnlyAttribute": false,
            "Required": true,
            "AttributeDataType": "String",
            "Mutable": false
          }
        ]
    }'
`

echo "Step 2/7: creating new user pool client"
client_id=`aws cognito-idp create-user-pool-client \
  --query UserPoolClient.ClientId \
  --output text \
  --cli-input-json \
    '{
        "UserPoolId": "'$pool_id'",
        "ClientName": "loanclub-web",
        "GenerateSecret": false,
        "SupportedIdentityProviders": ["COGNITO"],
        "ExplicitAuthFlows": ["USER_PASSWORD_AUTH"]
    }'
`

echo "Step 3/7: creating group LOAN_REQUESTOR"
group=`aws cognito-idp create-group \
  --group-name LOAN_REQUESTOR \
  --user-pool-id $pool_id`

echo "Step 4/7: creating test user"
username=`aws cognito-idp admin-create-user \
  --user-pool-id $pool_id \
  --username user@example.com \
  --user-attributes=Name=email,Value=user@example.com \
  --temporary-password P@ssw0rd_temp \
  --message-action SUPPRESS \
  --query User.Username \
  --output text`

echo "Step 5/7: adding user $username to group LOAN_REQUESTOR"
`aws cognito-idp admin-add-user-to-group \
  --user-pool-id $pool_id \
  --username $username \
  --group-name LOAN_REQUESTOR`

echo "Step 6/7: initiate auth"
session=`aws cognito-idp initiate-auth \
  --auth-flow USER_PASSWORD_AUTH \
  --auth-parameters=USERNAME=$username,PASSWORD=P@ssw0rd_temp \
  --client-id $client_id \
  --query Session \
  --output text`

echo "Step 7/7: temporary password change"
challenge_res=`aws cognito-idp respond-to-auth-challenge \
  --client-id $client_id \
  --challenge-name NEW_PASSWORD_REQUIRED \
  --session $session \
  --challenge-responses=USERNAME=$username,NEW_PASSWORD=P@ssw0rd`

echo ""
echo "All steps successfully executed"
echo ""

echo "pool_id: $pool_id"
echo "client_id: $client_id"
echo "username: $username"
echo "password: P@ssw0rd"