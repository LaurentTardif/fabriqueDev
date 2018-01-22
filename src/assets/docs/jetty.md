Docker Jetty
===================

Official Docker image : https://hub.docker.com/_/jetty/

* Docker compose :

```yml
version: "3"

services:
    jetty:
        image: jetty:9.4.7
        user: root
        restart: always
        ports:
            - 8087:8080
            - 8088:8443
        environment:
            - VIRTUAL_HOST=jetty.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8080
        volumes:
            - /home/snow/jetty/webapps:/var/lib/jetty/webapps
```