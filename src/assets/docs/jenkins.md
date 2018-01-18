Jenkins
============

Official docker image : https://hub.docker.com/_/jenkins/


Launch cmd `mkdir -p /home/walter/fabriq/jenkins`, then `sudo chown 1000 /home/walter/fabriq/jenkins` to authorize jenkins to write on the volume. 

* Docker compose :

```yml
version: "3"

services:
    jenkins:
        image: jenkins:2.46.1
        ports:
            - 8081:8080
        environment:
            - VIRTUAL_HOST=jenkins.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8080              
        volumes:
            - /home/walter/fabriq/jenkins:/var/jenkins_home 

```


* Connect to : http://jenkins.{{this.identityService.identity.ciDomain}}/

* Enter password found within the startup logs : `docker-compose logs jenkins`
* Select "select plugin to install" => then select plugins correspondig to your SCM + NodeJs Plugin
* "Next" (keep calm, download is coming)
* Create your user
* Manage Jenkins and add change the global tool configuration to a a 6.10.2 version of NodeJs
* Create a "freestyle" job, set up your repository URL (be carefull), provide Npm and node folder to your PATH and run shell script `npm install; npm run build --prod`
