#Dropwizard OAuth2 provider

Example OAuth2 provider project implementing the Resource Owner Password Credentials Grant in Dropwizard.

##Prerequisites
- Maven 3
- JDK 8 (probably works with 7 as well)

## Running the application
`mvn clean package && java -jar target/dropwizard-oauth2-provider-1.0-SNAPSHOT.jar server src/main/resources/config.yml`

Then browse to [localhost:8080/ping](http://localhost:8080/ping) or `curl localhost:8080/ping`.

