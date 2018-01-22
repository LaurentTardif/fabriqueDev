RocketChat
================

Official docker image : https://hub.docker.com/r/rocketchat/rocket.chat/
(you might need to launch the docker-compose twice since mongo takes times to init sometimesgit )

* Docker compose :

```yml
version: "3"

services:
  rocketchat:
    image: rocketchat/rocket.chat:latest
    restart: always
    volumes:
      - /home/snow/fabriq/rocketchat/uploads:/app/uploads
    environment:
      - PORT=3000
      - ROOT_URL=http://localhost:3000
      - MONGO_URL=mongodb://mongo:27017/rocketchat
      - MONGO_OPLOG_URL=mongodb://mongo:27017/local
      - MAIL_URL=smtp://smtp.email
      - VIRTUAL_HOST=rocketchat.{{this.identityService.identity.ciDomain}}
      - VIRTUAL_PORT=3000  
#       - HTTP_PROXY=http://proxy.domain.com
#       - HTTPS_PROXY=http://proxy.domain.com
    depends_on:
      - mongo
    ports:
      - 3000:3000
    labels:
      - "traefik.backend=rocketchat"
      - "traefik.frontend.rule=Host: your.domain.tld"

  mongo:
    image: mongo:3.2
    restart: always
    command: mongod --smallfiles --oplogSize 128 --replSet rs0
    labels:
      - "traefik.enable=false"

  # this container's job is just run the command to initialize the replica set.
  # it will run the command and remove himself (it will not stay running)
  mongo-init-replica:
    image: mongo:3.2
    restart: always
    command: 'mongo mongo/rocketchat --eval "rs.initiate({ _id: ''rs0'', members: [ { _id: 0, host: ''localhost:27017'' } ]})"'
    depends_on:
      - mongo

  # hubot, the popular chatbot (add the bot user first and change the password before starting this image)
  hubot:
    image: rocketchat/hubot-rocketchat:latest
    restart: always
    environment:
      - ROCKETCHAT_URL=rocketchat:3000
      - ROCKETCHAT_ROOM=GENERAL
      - ROCKETCHAT_USER=bot
      - ROCKETCHAT_PASSWORD=botpassword
      - BOT_NAME=bot
  # you can add more scripts as you'd like here, they need to be installable by npm
      - EXTERNAL_SCRIPTS=hubot-help,hubot-seen,hubot-links,hubot-diagnostics
    depends_on:
      - rocketchat
    labels:
      - "traefik.enable=false"
    volumes:
      - /home/snow/fabriq/rocketchat/scripts:/home/hubot/scripts
  # this is used to expose the hubot port for notifications on the host on port 3001, e.g. for hubot-jenkins-notifier
    ports:
      - 3001:8080

  #traefik:
  #  image: traefik:latest
  #  restart: unless-stopped
  #  command: traefik --docker --acme=true --acme.domains='your.domain.tld' --acme.email='your@email.tld' --acme.entrypoint=https --acme.storagefile=acme.json --defaultentrypoints=http --defaultentrypoints=https --entryPoints='Name:http Address::80 Redirect.EntryPoint:https' --entryPoints='Name:https Address::443 TLS.Certificates:'
  #  ports:
  #    - 80:80
  #    - 443:443
  #  volumes:
  #    - /var/run/docker.sock:/var/run/docker.sock

```