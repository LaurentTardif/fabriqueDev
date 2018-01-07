Docker Registry
===================

Official docker image : https://hub.docker.com/_/registry/

* Docker compose :

```yml
version: "3"

services:
    dockerhub:
        image: registry:2.6.2
        ports:
            - 5000:5000
        environment:
            - VIRTUAL_HOST=dockerhub.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=5000              
        volumes:
            - /home/snow/fabriq/dockerregistry/data:/var/lib/registry

```