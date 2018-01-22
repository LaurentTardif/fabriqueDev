Docker Mantis
===================

Docker image : https://hub.docker.com/r/vimagick/mantisbt/

* Docker compose :

```yml
version: "3"

services:
    mantisbt:
        image: vimagick/mantisbt:latest
        user: root
        restart: always
        ports:
            - "8989:80"
        links:
            - mysql
        restart: always
        environment:
            - VIRTUAL_HOST=mantis.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=80  
    mysql:
        image: mysql:latest
        user: root
        restart: always
        environment:
            - MYSQL_ROOT_PASSWORD=root
            - MYSQL_DATABASE=bugtracker
            - MYSQL_USER=mantisbt
            - MYSQL_PASSWORD=mantisbt
        restart: always

```

* Connet to http://<your_ci_uri>:8989/
* Enter in Hostname this value : mysql
* Enter in Username this value : mantisbt
* Enter in Password this value : mantisbt
* Enter in Database this value : bugtracker
* Enter in Admin Username this value : root
* Enter in Admin Password this value : root

* User is administrator
* Password is root