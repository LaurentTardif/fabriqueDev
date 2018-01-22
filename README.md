# Development fabrique

This project is a workshop to create a development frabrique

[Fabrique candidates](./src/assets/docs/Candidats.md)

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.6.3.

## Quick start

*Requirements*

- docker
- docker-compose

Create `docker-composer.yml`
```
version: '3'
services:
    nginx-proxy:
        image: norsys/fabriquedev-nginx-proxy:latest
        restart: always
        environment:
            - DEFAULT_HOST=fab.snow.ci
        ports:
            - "80:80"
        volumes:
            - /var/run/docker.sock:/tmp/docker.sock:ro
    fabriquedev:
        image: norsys/fabriquedev:latest
        restart: always
        environment:
            - VIRTUAL_HOST=fab.snow.ci
            
    # Put your tools below
```

Start fabrique

```
$ docker-compose up -d
```

*Go to http://localhost*

## Deploy to EC2

Replace `<your-security-group>` by a valid security group with port 80 and 22 allowed

```
$ aws ec2 run-instances \
    --image-id ami-4262d53f \
    --count 1 \
    --instance-type m3.xlarge \
    --user-data https://raw.githubusercontent.com/norsys/fabriqueDev/master/aws/userdata.yml \
    --security-groups <your-security-group> \
    --block-device-mappings https://raw.githubusercontent.com/norsys/fabriqueDev/master/aws/mapping.json
```

*Access to fabrique with `http://<public-ip>`*

*Connect on your instance with ssh:*
- user `snow`
- password `camp`

## Development

### Development server

Run `npm run start` for a start project on local. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `npm run build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

### Running unit tests

Run `npm run test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `npm run e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

### Use docker

*Requirements*

- docker
- docker-compose


```
make build
make start
make stop
make remove
```

### Deploy latest docker image online to EC2 instance

```
make aws-run-instances \
    AWS_ACCESS_KEY_ID=<your-access-key-id> \
    AWS_SECRET_ACCESS_KEY=<your-secret-access-key> \
    AWS_DEFAULT_REGION=<your-default-region> \
    SECURITY_GROUPS=<your-default-region>
```

*Security group must allowed port 80 and 22*

*Connect with ssh:*
- user `snow`
- password `camp`