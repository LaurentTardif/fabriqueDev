Docker Redmine
===================

docker image : https://hub.docker.com/_/redmine/

* Docker compose :

```yml
version: "3"

services:
    redminedb:
        image: mariadb
        environment:
            MYSQL_ROOT_PASSWORD: example
            MYSQL_DATABASE: redmine
        volumes:
            - /home/walter/fabriq/redmine/db:/var/lib/mysql


    redmine:
        image: redmine
        ports:
           - 3000:3000
        environment:
           REDMINE_DB_MYSQL: redminedb
           REDMINE_DB_PASSWORD: example
           VIRTUAL_HOST: redmine.{{this.identityService.identity.ciDomain}}
           VIRTUAL_PORT: 3000  
        volumes:
            - /home/walter/fabriq/redmine/files:/usr/src/redmine/files
        depends_on:
           - redminedb

```

* connectez vous sur 
http://<IP>:3000/
* connectez vous en tant qu'admin : admin/admin