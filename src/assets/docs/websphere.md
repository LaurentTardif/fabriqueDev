WebSphere
===================

Official docker image : https://hub.docker.com/_/websphere-liberty/

* Docker compose :

```yml
version: "3"

services:
    websphere:
        image: websphere-liberty:webProfile7
        restart: always
        ports:
            - 9080:9080
            - 9443:9443
        environment:
            - VIRTUAL_HOST=websphere.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=9080  
        volumes:
            - /home/snow/fabriq/websphere/yourwar.war:/config/dropins/yourwar.war
```
```