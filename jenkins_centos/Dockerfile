FROM cypress/browsers

# Install Requirements
RUN mkdir /src
RUN cd /src \
    && git clone http://172.16.8.204:8080/root/productbox.git
RUN cd /src/productbox \
    && git checkout feature/cypress
RUN cd /src/productbox/front-cypress \ 
    && npm install
RUN cd /src/productbox/front-cypress \
    && ./node_modules/.bin/cypress run --reporter mochawesome
