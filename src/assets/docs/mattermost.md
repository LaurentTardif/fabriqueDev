Mattermost
==============

Official docker image : https://hub.docker.com/r/mattermost/platform/

* Docker compose :

```yml
version: "3"

services:
    mattermost:
        image: mattermost/mattermost-preview:4.6
        ports:
            - 8077:8065
        environment:
            - VIRTUAL_HOST=mattermost.{{this.identityService.identity.ciDomain}}
            - VIRTUAL_PORT=8065  
```