# Quickstart Guide
## Introduction  
Multipart objects using presigned URLs, upload directly to the server without transiting through the business server.  

This is just a simple demo, please improve the code according to actual business.

## Integration example
curl example:
1. Init multipart upload
```shell script
$ curl --location --request POST '127.0.0.1:8006/multipart/init' \
--header 'Content-Type: application/json' \
--data-raw '{
    "filename": "b.jpg",
    "partCount": 2,
    "contentType": "image/jpeg"
}'
```
Response example:
```js
{
    "uploadId": "b7dd9a60-7c11-43f1-acee-bffd4ef2fccb",
    "uploadUrls": [
        "https://play.minio.io:9000/tuinetest/test/b.jpg?uploadId=b7dd9a60-7c11-43f1-acee-bffd4ef2fccb&partNumber=1&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20210324%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210324T032112Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=e39c8e8c165add0daa50d2da44e51ca752b9213e497633bcfb3431b60383b5be",
        "https://play.minio.io:9000/tuinetest/test/b.jpg?uploadId=b7dd9a60-7c11-43f1-acee-bffd4ef2fccb&partNumber=2&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20210324%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210324T032112Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=99611a212d6b791a24df295cb3475a79780ed4c6314ee9ddb8df4179326b7723"
    ]
}
``` 

2. Uploading objects using presigned URLs  

Cut the picture into two parts
```shell script
curl --location --request PUT 'https://play.minio.io:9000/tuinetest/test/b.jpg?uploadId=b7dd9a60-7c11-43f1-acee-bffd4ef2fccb&partNumber=1&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20210324%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210324T032112Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=e39c8e8c165add0daa50d2da44e51ca752b9213e497633bcfb3431b60383b5be' \
--header 'Content-Type: application/octet-stream' \
--data-binary '@/D:/jpgone'
curl --location --request PUT 'https://play.minio.io:9000/tuinetest/test/b.jpg?uploadId=b7dd9a60-7c11-43f1-acee-bffd4ef2fccb&partNumber=2&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20210324%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210324T032112Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=99611a212d6b791a24df295cb3475a79780ed4c6314ee9ddb8df4179326b7723' \
--header 'Content-Type: application/octet-stream' \
--data-binary '@/D:/jpgtwo'
```

3. Complete 
```shell script
curl --location --request PUT '127.0.0.1:8006/multipart/complete' \
--header 'Content-Type: application/json' \
--data-raw '{
    "objectName":"test/b.jpg",
    "uploadId":"b7dd9a60-7c11-43f1-acee-bffd4ef2fccb"
}'
```

## Verify upload 

Login Minio: [play MinIo](https://play.minio.io:9000/minio/tuinetest/)  
Username: Q3AM3UQ867SPQQA43P2F  
Password: zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG  