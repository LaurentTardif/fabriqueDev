Nexus
============

Official docker image : https://hub.docker.com/r/sonatype/nexus3/

Launch cmd `sudo mkdir -p /home/walter/fabriq/nexus`, then `sudo chown 200 /home/walter/fabriq/nexus` to be autorised to write on the volume.   

* Docker compose :

```yml
version: "3"

services:
    nexus:
        image: sonatype/nexus3:3.2.1
        volumes:
            - "/home/walter/fabriq/nexus:/nexus-data"
        ports:
            - 8082:8081 
        environment:
            - VIRTUAL_HOST=nexus.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8081  

```

* Connect to 
http://nexus.{{this.identityService.identity.ciDomain}}:8082/
* Admin credentials are : admin/admin123

Modify your Jenkins job :
* Create archive after build step : `tar -zcvf fabriq.tar.gz dist`
* Send it to Nexus : `curl -v -u admin:admin123 --upload-file ./fabriq.tar.gz http://nexus.{{this.identityService.identity.ciDomain}}:8082/repository/fabriq/fabriq.tar.gz`
(this can also be done using Nexus specific plugin)





