## loanclub-api

#### Prerequisites

Make sure you have:

- [Docker](https://docs.docker.com/install)
- [Docker Compose](https://docs.docker.com/compose/install)
- An active [AWS account](https://aws.amazon.com/console)
- [AWS CLI](https://github.com/aws/aws-cli) with configured AWS credentials

#### How to run

In order to run the app locally:

1. Configure an AWS Cognito user pool by executing: 

    `./deployment/scripts/aws-cognito-setup.sh`
    
    Write down the output params.

2. Generate an `application.yml` config file by executing:

    `./deployment/scripts/generate-config.sh <pool_id>`
    
3. Build the application

    `./mvnw clean install`
    
4. Run the application:
  
    `docker-compose -f ./deployment/compose/docker-compose.yml up`  
    
5. Obtain an access token from AWS Cognito by executing

    `./deployment/scripts/aws-cognito-auth.sh <username> <password> <client_id>`
    
    Attach this token in the `Authorization` header while executing requests.