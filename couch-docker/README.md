# Custom Couchbase

### User

Creates a user as below
* Username - Administrator
* Password - password

### Bucket

Loads a bucket as below:

* BucketName - tinyurls
* Memory Quota - 100MB
* authType - NONE

## Usage

* Navigate to the couchbase folder<br>
``` cd couchbase/ ```

* Execute the ``` buildImage ``` script

```bash
./buildImage.sh
```

## Running the docker

```bash
docker run -d -p 8091-8094:8091-8094 -p 11210:11210 tinycouch:1.0
```




