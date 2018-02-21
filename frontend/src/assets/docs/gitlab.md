Gitlab
============

Official docker image : https://hub.docker.com/r/gitlab/gitlab-ce/


* Docker compose :

```yml
version: "3"

services:
    gitlab:
        image: gitlab/gitlab-ce:10.3.3-ce.0
        user: root
        restart: always
        hostname: gitlab.{{this.identityService.identity.ciDomain}}
        ports:
            - 8080:80
            - 8443:443
        environment:
            - VIRTUAL_HOST=gitlab.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=80                   
        volumes:
            - /home/snow/fabriq/gitlab/config:/etc/gitlab 
            - /home/snow/fabriq/gitlab/logs:/var/log/gitlab 
            - /home/snow/fabriq/gitlab/data:/var/opt/gitlab 

```

* Connect to :
http://gitlab.{{this.identityService.identity.ciDomain}}/

* Change `root` password
* New project => import project => git repo by url : https://github.com/LaurentTardif/fabriqueDev.git
* Set project as public project
