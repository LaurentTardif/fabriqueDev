Verdaccio
===================

Docker image : https://hub.docker.com/r/verdaccio/verdaccio/ 

* Docker compose :

```yml
version: "3"

services:
    verdaccio:
        image: verdaccio/verdaccio:2.7.3
        container_name: verdaccio
        ports:
            - "8092:4873"
        environment:
            - VIRTUAL_HOST=verdaccio.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=4873
        volumes:
            - "/home/snow/fabriq/verdaccio/storage:/verdaccio/storage"
            - "/home/snow/fabriq/verdaccio/conf:/verdaccio/conf"
```