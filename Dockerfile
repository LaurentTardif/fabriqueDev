FROM ubuntu:14.04

# Install Requirements
RUN apt-get update -qq \
    && apt-get install -qy \
        curl

# Install Node JS & NPM
RUN curl -sL https://deb.nodesource.com/setup_6.x | bash - \
    && apt-get update -qq \
    && apt-get install -qy nodejs

# Install Angular CLI
RUN npm install -g @angular/cli

# Build project
COPY . /src
RUN cd /src \
    && npm install \
    && npm run build --prod

WORKDIR /src
EXPOSE 4200

CMD npm start
