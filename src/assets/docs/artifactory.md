Artifactory
===================

Docker image : https://bintray.com/jfrog/reg2/jfrog:artifactory-oss


* Docker compose :

```yml
version: "3"

services:
    artifactory:
        image: docker.bintray.io/jfrog/artifactory-oss:5.8.3
        restart: always
        ports:
            - 8092:8081
        environment:
            - VIRTUAL_HOST=artifactory.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8081
    #    Add extra Java options by uncommenting the following lines
    #    environment:
    #     - EXTRA_JAVA_OPTIONS=-Xmx4g

```

The default user is : admin/password