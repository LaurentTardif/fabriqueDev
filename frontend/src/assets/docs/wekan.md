Wekan
================

Docker image : https://hub.docker.com/r/mquandalle/wekan/

* Docker compose :

```yml
version: "3"

services:
    wekan:
        image: mquandalle/wekan:latest
        user: root
        restart: always
        ports:
            - 8086:80
        environment:
            - MONGO_URL=mongodb://wekandb:27017/wekan
            - ROOT_URL=http://wekan.snow.ci
            - VIRTUAL_HOST=wekan.snow.ci
            - VIRTUAL_PORT=80  

    wekandb:
        image: mongo:3.2.18
        restart: always
        container_name: wekan-db
        command: mongod --smallfiles --oplogSize 128
        expose:
            - 27017

```

* Create your own user (a 500 HTTP error might happened, this can be ignored)