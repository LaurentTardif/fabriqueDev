# Development fabrique

This project is a workshop to create a development frabrique. It's based on the session presented at the snowcamp
by : @cedvanet and @StalinAntoine 


This project is maintened by Laurent Tardif, @ouelcum. 

I added traeffik and update some components to build a forge demonstration project.


-------------------- 
# How to configure it 

 * Update your /etc/hosts to allow easy access to the services

Example of /etc/hosts 

	127.0.0.1   grafana.snow.ci
	127.0.0.1   prometheus.snow.ci
	127.0.0.1   fab.snow.ci gitlab.snow.ci jenkins.snow.ci nexus.snow.ci sonar.snow.ci 
	127.0.0.1   jira.snow.ci rundeck.snow.ci nginx.snow.ci mattermost.snow.ci
	127.0.0.1   artifactory.snow.ci




 * copy the prometheus/prometheus.yml in /tmp
 * configure the .env file to match your installation (mainly the domain you want to use, and the path where you want to put configuration files)

example of .env file : 

	DOMAIN=snow.ci
	DATA_PATH=/home/snow/ci/

# How to fetch specific stuff

For artifactory : docker pull docker.bintray.io/jfrog/artifactory-oss:latest

# How to run it 

 * Docker-compose -f docker-compose.yml start # will start a minimal forge : traefik, promotheus, jenkins and gitlab 
 * To run additional component add the docker-compose you need in the command line ex :
    docker-compose -f docker-compose.yml -f docker-compose-Jira.yml start

 
#GITLAB 
  default user : admin@example.com

#Jenkins 

