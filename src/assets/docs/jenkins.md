Jenkins
============

Official docker image : https://hub.docker.com/r/jenkins/jenkins/


Launch cmd `mkdir -p /home/snow/fabriq/jenkins`, then `sudo chown 1000 /home/snow/fabriq/jenkins` to authorize jenkins to write on the volume. 

* Docker compose :

```yml
version: "3"

services:
    jenkins:
        image: jenkins/jenkins:2.102
        ports:
            - 8081:8080
            - 50000:50000
        environment:
            - VIRTUAL_HOST=jenkins.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8080              
        volumes:
            - /home/snow/fabriq/jenkins:/var/jenkins_home

```


* Connect to : http://jenkins.{{this.identityService.identity.ciDomain}}/

* Enter password found within the startup logs : `docker-compose logs jenkins`
* Select "select plugin to install" => then select plugins correspondig to your SCM + NodeJs Plugin
* "Next" (keep calm, download is coming)
* Create your user
* Manage Jenkins and add change the global tool configuration to a a 9.4.0 version of NodeJs
* Create a "freestyle" job, set up your repository URL (be carefull), provide Npm and node folder to your PATH and run shell script `npm install; npm run build --prod`
