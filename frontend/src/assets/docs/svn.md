SVN
==============

Docker image : https://hub.docker.com/r/marvambass/subversion/

* Docker compose :

```yml
version: "3"

services:
    svn:
        image: marvambass/subversion
        user: root
        restart: always
        ports:
            - 8090:80
        environment:
            - VIRTUAL_HOST=svn.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=80  
        volumes:
            - /home/snow/fabriq/svn/local:/var/local/svn
            - /home/snow/fabriq/svn/backup:/var/svn-backup
            - /home/snow/fabriq/svn/dav:/etc/apache2/dav_svn/

```