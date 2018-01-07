Mercurial
============

Docker image :  https://hub.docker.com/r/miguillo/mercurial-server/

Before launch : `docker run -v /home/snow/fabriq/mercurial:/var/lib/mercurial-server/repos -p 22222:2222 -e HG_ROOTUSER_KEYS="$(ssh-add -L)" miguillo/mercurial-server` to generate SSH keys


* Docker compose :

```yml
version: "3"

services:
    mercurial:
        image: miguillo/mercurial-server:20160708-1
        ports:
            - 8100:2222
        environment:
            - VIRTUAL_HOST=mercurial.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=2222  
        volumes:
            - /home/snow/fabriq/mercurial/:/var/lib/mercurial-server/repos
```

