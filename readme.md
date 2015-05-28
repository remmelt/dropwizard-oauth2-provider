#Dropwizard OAuth2 provider

Example OAuth2 provider project implementing the Resource Owner Password Credentials Grant in Dropwizard.

##Prerequisites
- Maven 3
- JDK 8 (probably works with 7 as well)

## Running the application
`mvn clean package && java -jar target/dropwizard-oauth2-provider-1.0-SNAPSHOT.jar server src/main/resources/config.yml`

Then browse to [localhost:8080/ping](http://localhost:8080/ping) or `curl localhost:8080/ping`.

##Usage Demo:

 Step 1 [Generate Token]:

        [valid user]

        curl -d "username=alice" \
             -d "password=secret" \
             -d "grant_type=password" \
             localhost:8080/oauth2/token

        [invalid user]

        curl -d "username=bad-alice" \
             -d "password=secret" \
             -d "grant_type=password" \
             localhost:8080/oauth2/token

 Step 2 [Call oAuth2.0 Protected Resource By Passing "XXXXX-XXXXX-XXXX-XXXXX" Access Token) Generated At Step 1]:

    curl --header "Authorization: Bearer XXXXX-XXXXX-XXXX-XXXXX" --include --request GET "http://localhost:8080/ping/auth"


