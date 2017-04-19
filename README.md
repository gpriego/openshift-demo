# PDP Skeleton

Skeleton for building REST-Full servicies and docker images. 

The skeleton cover the following tools:

 - Spring Boot and Spring Web
 - JWT Security
 - Docker Images

## JWT Security 

Skeleton uses the JWT Security artifacts of serenity. This enable security feaatures in private environments.

Consumig the services requires to send a token in the request header. This is an call example.

>GET /hello HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFwcENsaWVudF9SUzI1NiJ9.eyJpc3MiOiJBcHBDbGllbnQiLCJzdWIiOiJ1aWQ6bjExMTExMSIsImF1ZCI6IlNrZWxldG9uQVVEIiwibmJmIjoxNDMyMTE5MzYyLCJleHAiOjE1MjQxMjc5MTQsImlhdCI6MTQzMjExOTM2MiwianRpIjoiQjQ2NTc4QzIwRDlGN0UwMDA4NzBGNkEyIn0.P_tmHBpD6IvZznO8UIMjJRougim12Jkj0UqwyEFddvieK2YqwOrRicI2LLYYjdtpPxcCdss7NZkV_1nQTNj8ZiFXV9CjClzlD8Bk9zM2pdebDTiaSzyu9nxi8KfqtNAoNWab1uuGf3MKIFMqcXbZkaad86jDFDmZpoREeY_fbRQ

As you can seem the token is send in the Authorization Header, putting before the key work "Bearer".

### Building the token

The token is composed by two fields, Header and Payload.

#### Header
|Field|Description|
|----|--------|
|alg|Fixed to RD256
|typ|Fixed to JWT
|kid| <fild iss of payload>_<field alg of head>

#### Payload
|Field|Description|
|----|--------|
|iss|Token generator
|sub|Logged user
|aud|Audience allowed in the service (must be the service name)
|nbf|Time when the token starts to be valid
|exp|Life of the token in seconds
|iat|Time of token generation
|jti|Id of the generated token

Finally, to build the token you must follow next steps:
1.- Generate a private and a public key in Base64 format. Using Eclipse or OpenSSL, for instance.
2.- Build the token with the structure described before.
3.- Sign the header and the payload of the token. Each of both in is sign.
4.- Join header and payload signs by a dot.

#### Skeleton Config
This is an example of configuration file for jwt.

```
serenity.security:
   audience: SkeletonAUD
   keyprovider:
      publicKeys:
         - 
            alias: AppClient_RS256
            encodedKey: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7Clmmz6z7sm3/AYl1l+ihIKnt3ZIP30LJp6PkmY10N3ZnLyUlv5lW7hkDffPiYI00vx+9Wfh/ggONUvQDZoY/cOBL25dCrdGdO49g1e5jTARmv/kl5iBz+jxU4AJnbg4YiOphYhN0B99qYjVb2t7Q/I8t3qsvaIjsuhYed2P2nwIDAQAB
```

In this case, you would be using an InMemoryKeyProvider. For this to work, you have to follow the following tips:
- serenity.security.audience must be the same as the fiel aud of the token
- serenity.security.keyprovider.publicKeys[n].alias must be the same as kid field in token header
- serenity.security.keyprovider.publicKeys[n].encodedKey is the public key previously generated.

## Docker

Skeleton is prepared to build docker image. This is done by the org.jolokia.docker-maven-plugin. 
Only it is necesary to run the following maven sentence.
>mvn clean package docker:build