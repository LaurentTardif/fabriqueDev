SonarQube
============

Official docker image : https://hub.docker.com/_/sonarqube/


* Docker compose :

```yml
version: "3"

services:
    sonarqube:
        image: sonarqube:6.7.1
        ports:
            - 8085:9000
        environment:
            - VIRTUAL_HOST=sonar.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=9000  
        volumes:
            - /home/snow/fabriq/sonarqube/conf:/opt/sonarqube/conf
            - /home/snow/fabriq/sonarqube/data:/opt/sonarqube/data
            - /home/snow/fabriq/sonarqube/extensions:/opt/sonarqube/extensions
            - /home/snow/fabriq/sonarqube/bundled-plugins:/opt/sonarqube/lib/bundled-plugins

```

* Connect to : http://sonar.{{this.identityService.identity.ciDomain}}/
* Admin credentials are : admin/admin
* Install SonarJs plugin (don't forget to restart sonarqube : `docker-compose start/stop sonarqube`)

Configure Jenkins :
* Install plugin `SonarQube Scanner for Jenkins`
* Fill `SonarQube servers` information in Jenkins configuration

Configure project :
* Add `sonar-project.properties` file at the root of your project :

```yml
sonar.projectKey=fr.snowcamp:fabriq
sonar.projectName=Snowcamp :: Ma fabrique de neige
sonar.projectVersion=1.0  
sonar.language=js 
sonar.sources=src 
sonar.sourceEncoding=UTF-8

```

* Add build step to execute Sonarqube scanner (nothing to fill)
* Launch your job


