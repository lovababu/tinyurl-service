# Tiny URL service
  Restful webservice takes the long http URL, and convert it into shorten url.

## Tech Stack
    - Java 1.8
    - SpringBoot
    - Couchbase
    - Lombok
    - Logback

## Prerequisites
    - Java 1.8
    - Gradle
    - Docker
    - Any IDE

## How to Run?

### From IDE
    
  Open the class called ```TinyUrlApp.java``` and run as java application.
  
### From command line
  jump into root directory and
  
  Run the command ```./gradlew bootRun```
  
## Health check.

  Browse for ```http://<HOST:PORT>/avol/manage/health``` as per the default configuration.
  
## Sample Requests.

### To short the URL.

Request:

    curl -X POST \
      http://localhost:8080/avol/urls \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 8382ce39-888f-2323-e882-21dd34c66736' \
      -d '{
    	"originalUrl":"http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services"
    }'
    
Response:

    {
        "shortUrl": "http://localhost:8080/bmapHM",
        "expiryTime": "2018-12-08T17:13:10.566"
    } 

### Redirect to shorten URL.

Take the shortUrl from the post response and browse, it should redirect to actual url.  
  
  