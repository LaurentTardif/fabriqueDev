Teamscale
============

Docker image : https://hub.docker.com/r/cqse/teamscale/


* Docker compose :

```yml
version: "3"

services:
    teamscale:
        image: cqse/teamscale:3.9.0
        user: root
        restart: always
        volumes:
            - /home/snow/fabriq/teamscale/storage:/opt/teamscale/storage
            - /home/snow/fabriq/teamscale/backup:/backup
        ports:
            - 8096:8080
        environment:
            - VIRTUAL_HOST=teamscale.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8080  
```

