Teamcity
===================

offical docker image : https://hub.docker.com/r/jetbrains/teamcity-server/ 


* Docker compose :

```yml
version: "3"

services:
     teamcity:
        image: jetbrains/teamcity-server:2017.2.1
        volumes:
            - /home/snow/fabriq/teamcity/home:/data/teamcity_server/datadir
            - /home/snow/fabriq/teamcity/logs:/opt/teamcity/logs
        ports:
            - 8101:8111
        environment:
            - VIRTUAL_HOST=teamcity.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8111  

```