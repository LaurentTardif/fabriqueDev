Zulip
================

Official docker image : https://hub.docker.com/r/galexrt/zulip/

* Docker compose :

```yml
version: "3"

services:
    database:
        image: "quay.io/galexrt/postgres-zulip-tsearchextras:latest"
        restart: always
        environment:
            POSTGRES_DB: zulip
            POSTGRES_USER: zulip
            POSTGRES_PASSWORD: zulip
        volumes:
            - "/home/snow/fabriq/zulip/postgresql/data:/var/lib/postgresql/data:rw"

    memcached:
        image: "quay.io/sameersbn/memcached:latest"
        restart: always

    rabbitmq:
        image: "rabbitmq:3.5.5"
        hostname: zulip-rabbit
        restart: always
        environment:
            RABBITMQ_DEFAULT_USER: "zulip"
            RABBITMQ_DEFAULT_PASS: "zulip"
 
    redis:
        image: "quay.io/sameersbn/redis:latest"
        restart: always
        volumes:
            - "/home/snow/fabriq/zulip/redis:/var/lib/redis:rw"

    zulip:
        image: "quay.io/galexrt/zulip:1.5.1-5"
        restart: always
        ports:
            - "8383:80"
            - "7443:443"
        environment:
            DB_HOST: "database"
            DB_USER: "zulip"
            DB_PASS: "zulip"
            DB_PASS: "zulip"
            SETTING_MEMCACHED_LOCATION: "memcached:11211"
            SETTING_RABBITMQ_HOST: "rabbitmq"
            SETTING_REDIS_HOST: "redis"
            SECRETS_email_password: "123456789"
            SECRETS_rabbitmq_password: "zulip"
            SECRETS_secret_key: "REPLACE_WITH_SECURE_SECRET_KEY"
            SETTING_EXTERNAL_HOST: "example.com"
            SETTING_ZULIP_ADMINISTRATOR: "admin@example.com"
            SETTING_ADMIN_DOMAIN: "example.com"
            SETTING_NOREPLY_EMAIL_ADDRESS: "example@example.fr"
            SETTING_DEFAULT_FROM_EMAIL: "Zulip <noreply@example.com>"
            SETTING_EMAIL_HOST: "smtp.example.com"
            SETTING_EMAIL_HOST_USER: "noreply@example.com"
            SETTING_EMAIL_PORT: "587"
            # It seems that the email server needs to use ssl or tls and can't be used without it
            SETTING_EMAIL_USE_SSL: "False"
            SETTING_EMAIL_USE_TLS: "True"
            SETTING_ALLOWED_HOSTS: "[ '*', '127.0.0.2' ]"
            ZULIP_AUTH_BACKENDS: "EmailAuthBackend"
            ZULIP_USER_CREATION_ENABLED: "True"
            ZULIP_USER_EMAIL: "example@example.com"
            ZULIP_USER_PASS: "zulip"
            ZULIP_USER_DOMAIN: "example.com"
            ZULIP_USER_FULLNAME: "Zulip Example User"
            VIRTUAL_HOST: zulip.{{this.identityService.identity.ciDomain}}
            VIRTUAL_PORT: 80  
        volumes:
            - "/home/snow/fabriq/zulip/zulip:/data:rw"

```