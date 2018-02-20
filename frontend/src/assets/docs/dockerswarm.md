Docker Swarm
===================

* Init Docker Swarm cluster:

```yml
docker swarm init  
```

* Join Swarm cluster:

```yml
 docker swarm join \
    --token <TOKEN> \
    <MASTER_IP>
```

* Verify your cluster:

```yml
docker node ls
```

* Add in your docker-compose.yml all volumes, and change path for your container:

```yml
volumes:
  db-jenkins-data:
  db-redmine-data:
```

* Deploy your CI:

```yml
docker deploy --compose-file docker-compose.yml myci
```