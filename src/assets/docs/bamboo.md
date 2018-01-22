Bamboo
===================

Docker image : https://hub.docker.com/r/cptactionhank/atlassian-bamboo/

You'll need a trial licence to use Bamboo.

* Docker compose :

```yml
version: "3"

services:
    bamboo:
        image: cptactionhank/atlassian-bamboo:6.3.0
        user: root
        restart: always
        ports:
            - 8091:8085
        environment:
            - VIRTUAL_HOST=bamboo.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8085              
        volumes:
            - /home/snow/fabriq/bamboo/data:/var/atlassian/bamboo
            - /home/snow/fabriq/bamboo/logs:/opt/atlassian/bamboo/logs

```