Docker Tomcat
===================

Official docker image : https://hub.docker.com/_/tomcat/

* Docker compose :

```yml
version: "3"

services:
    tomcat:
        image: tomcat:9
        ports:
            - 9090:8080
        environment:
            - VIRTUAL_HOST=tomcat.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8080  
        volumes:
            - /home/walter/tomcat/ROOT:/usr/local/tomcat/webapps/ROOT
```