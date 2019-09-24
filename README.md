### loanclub-api

#### Development

In order to run the app locally:

1. Configure an user pool in AWS Cognito and set the appropriate `jwk-set-uri` in `application.yml`
2. Run a MongoDB instance using docker:

    `docker run -d -p "27017:27017" --name loanclub_db mongo:3.7.2-jessie`
    
3. Run the application:
  
    `./mvnw spring-boot:run`  