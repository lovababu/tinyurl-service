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
    - couchbase server
    - Any IDE

## setup and configuration

### Docker setup

   Required only when you are planning launch couchbase server as container.
   
   [Refer](https://docs.docker.com/toolbox/overview/)
   
### Couchbase setup.

 **Windows**  
    [Please refer this link](https://developer.couchbase.com/documentation/server/current/install/install-package-windows.html)
    
 **MacOS/Linux**   
    [Docker setup](couch-docker/README.md)

 In case couchbase as docker container, script will take care care of setup cluster and creating default bucket.  
 In case couchbase as standalone server, you have to setup cluster and create default bucket from the couchbase console.

### App setup.

  [application.yml](src/main/resources/application.yml) has a default configuration, change if needed.

## Build.

  To build the project run ```./gradlew build```
  
  It creates executable jar file named ```tinyurl-service-0.0.1-SNAPSHOT.jar``` under the `build/libs` directory.
  
  cucmber test reports can be found in `build/reports` directory

## How to Run?

### From IDE
    
  Open the class called ```TinyUrlApp.java``` and run as java application.
  
### From command line
  jump into root directory and
  
  Run the command ```./gradlew bootRun```
  
### Run the executable jar file.

   ```java -jar tinyurl-service-0.0.1-SNAPSHOT.jar```
  
  It runs the server on configured port default is 8080.
  
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
        "shortUrl": "http://localhost:8080/avol/urls/bmapHM",
        "expiryTime": "2018-12-08T17:13:10.566"
    } 

### Redirect to shorten URL.

Take the shortUrl from the post response and browse, it should redirect to actual url.  
  
  