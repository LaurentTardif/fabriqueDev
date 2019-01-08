# fdev

# Foreword

## Compatibility

This project was mostly developed on macOS Mojave (10.14), with Docker for mac version `18.09.0, build 4d60db4`. It's known to work well on Linux too, but if you can use macOS I'd recommend doing so.

## Requirements

- docker (via docker machine, docker toolbox, ... doesn't really matter)
- docker-compose

## Hardware

When fully loaded, this project will consume **lots** of CPU and RAM. Really. **LOTS**. I recommend *at least* 16G of RAM, and a decent recent CPU, and even with that, you can expect some latency and slowness, especially during a Jenkins build.

# Installation

**Important:** Because GitHub's maximum file size is 100M, the data archive had to be split into smaller chunks. Before running this project, you need to merge them back together. To do so, run: 

````bash
cd archive
cat archive.parta* > archive.tar.gz
mv data ..
cd ..
rm -fr archive # optional
````

If you make changes to the data folder and want to upload them, you can create your own splitted version by running the following :

````bash
rm -fr archive
mkdir archive
GZIP=-9 tar -zcvf archive.tar.gz data
split -b 80M archive.tar.gz "archive/archive.part" # On macOS, you may need to use 80m with a lowercase 'M'
rm archive.tar.gz
````

# Usage

## Getting started

First, update your `/etc/hosts` file with the following settings: 

````
127.0.0.1   grafana.snow.ci
127.0.0.1   prometheus.snow.ci
127.0.0.1   fab.snow.ci gitlab.snow.ci jenkins.snow.ci nexus.snow.ci sonar.snow.ci
127.0.0.1   jira.snow.ci rundeck.snow.ci nginx.snow.ci mattermost.snow.ci
127.0.0.1   artifactory.snow.ci
127.0.0.1   api.snow.ci
127.0.0.1   doc.snow.ci
````

Then, you can start the whole system with just : 

```docker-compose up # optionally with -d if you don't need to look at the logs```

Everything should work out-of-the-box. 

## Passwords

All services are set-up with these default credentials : 

- Username: admin
- Password: admin

With the notable exception of GitLab, which uses `admin@snow.ci` and `adminadmin`. 

# Security

This project is educational, and assumes you'll be working on your local machine. As such, almost no precautions were taken regarding security. I don't recommend running this in production. 
