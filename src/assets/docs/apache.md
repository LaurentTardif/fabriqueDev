HTTPD
============

Official docker image : https://hub.docker.com/_/httpd/


* Docker compose :

```yml
version: "3"

services:
    httpd:
        image: httpd:2.4.29
        ports:
            - 8095:80
        environment:
            - VIRTUAL_HOST=apache.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=80                   
        volumes:
            - /home/snow/fabriq/httpd/html:/usr/local/apache2/htdocs/ 
```

