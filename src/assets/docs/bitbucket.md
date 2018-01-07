Bitbucket
============

Docker image : https://hub.docker.com/r/cptactionhank/atlassian-bitbucket/ 

You'll need a trial licence to use Bitbucket.

* Docker compose :

```yml
version: "3"

services:
    bitbucket:
        image: cptactionhank/atlassian-bitbucket:4.14.4
        volumes:
            - /home/snow/fabriq/bitbucket/home:/var/atlassian/bitbucket
            - /home/snow/fabriq/bitbucket/logs:/opt/atlassian/bitbucket/logs
        environment:
            - VIRTUAL_HOST=bitbucket.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=7990              
        ports:
            - 8200:7990

```

