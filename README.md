# Development fabrique

This project is a workshop to create a development frabrique. It's based on the session presented at the snowcamp
by : @cedvanet and @StalinAntoine 


This project is maintened by Laurent Tardif, @ouelcum. 

I added traeffik and update some components to build a forge demonstration project.


-------------------- 
# How to configure it 

 * copy the prometheus/prometheus.yml in /tmp
 * configure the .env file to match your installation (mainly the domain you want to use, and the path where you want to put configuration files)

example of .env file : 

	DOMAIN=snow.ci
	DATA_PATH=/home/snow/ci/


# How to run it 

 * Docker-compose -f docker-compose.yml start # will start a minimal forge : traefik, promotheus, jenkins and gitlab 
 * To run additional component add the docker-compose you need in the command line ex :
    docker-compose -f docker-compose.yml -f docker-compose-Jira.yml start

 
