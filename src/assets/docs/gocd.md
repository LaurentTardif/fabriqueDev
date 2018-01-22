gocd
==============

Official Docker image :  
https://hub.docker.com/r/gocd/gocd-server/

* Docker compose :

```yml
version: "3"

services:
    gocd-server:
        image: gocd/gocd-server:v17.12.0
        restart: always
        ports:
            - 8093:8153
            - 8094:8154
        environment:
            - VIRTUAL_HOST=gocd.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8153  
        volumes:
            - /home/snow/fabriq/godata/addons:/godata/addons
            - /home/snow/fabriq/godata/artifacts:/godata/artifacts
            - /home/snow/fabriq/godata/config:/godata/config
            - /home/snow/fabriq/godata/db:/godata/db
            - /home/snow/fabriq/godata/logs:/godata/logs
            - /home/snow/fabriq/godata/plugins:/godata/plugins
            - /home/snow/fabriq/godata/go:/home/go   

```