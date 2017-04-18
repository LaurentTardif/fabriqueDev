Nginx
============

Official docker image : https://hub.docker.com/_/nginx/


* Docker compose :

```yml
version: "3"
services:
    nginx:
        image: nginx:1.11
        ports:
            - 8084:80
        environment:
            - VIRTUAL_HOST=nginx.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=80            
        volumes:
            - /home/walter/fabriq/shared:/usr/share/nginx/html
```