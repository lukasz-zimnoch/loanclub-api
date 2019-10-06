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

    `./scripts/aws-cognito-setup.sh`
    
    Write down the output params.

2. Generate an `application.yml` config file by executing:

    `./scripts/generate-config.sh <pool_id>`
    
3. Run a MongoDB instance using docker:

    `docker run -d -p "27017:27017" --name loanclub_db mongo:3.7.2-jessie`
    
4. Run the application:
  
    `./mvnw spring-boot:run`  
    
5. Obtain an access token from AWS Cognito by executing

    `./scripts/aws-cognito-auth.sh <username> <password> <client_id>`
    
    Attach this token in the `Authorization` header while executing requests.