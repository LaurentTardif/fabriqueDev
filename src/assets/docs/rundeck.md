Rundeck
============

Official docker image : https://hub.docker.com/r/jordan/rundeck/

Launch cmd `sudo mkdir -p /home/snow/fabriq/shared`, then `sudo chmod 777 /home/snow/fabriq/shared` to be autorised to write on the volume.  

* Docker compose :

```yml
version: "3"

services:
    rundeck:
        image: jordan/rundeck:2.10.2
        user: root
        restart: always
        ports:
            - 8083:4440
        volumes:
            - /home/snow/fabriq/rundeck/etc:/etc/rundeck
            - /home/snow/fabriq/rundeck/var:/var/rundeck
            - /home/snow/fabriq/rundeck/varlog:/var/log/rundeck
            - /home/snow/fabriq/rundeck/plugins:/opt/rundeck-plugins
            - /home/snow/fabriq/rundeck/log:/var/lib/rundeck/logs
            - /home/snow/fabriq/rundeck/data:/var/lib/rundeck/var/storage
            - /home/snow/fabriq/shared:/tmp/fabriq/shared
        environment:
            - RUNDECK_ADMIN_PASSWORD=admin
            - SERVER_URL=http://localhost:8083
            - VIRTUAL_HOST=rundeck.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=4440  
```

* Connect to : http://rundeck.{{this.identityService.identity.ciDomain}}/
* Admin credentials are : admin/admin
* Create a Project (use `stub`)
* Create a job
* Add command step to upload artefact : `curl -v -u admin:admin123 -O http://nexus.{{this.identityService.identity.ciDomain}}/repository/fabriq/fabriq.tar.gz`
* Add command step to untar the archive : `tar -zxf fabriq.tar.gz`
* Add command step to move content to shared folder : `cp -rf ./dist/* /tmp/fabriq/shared`
* Run the job