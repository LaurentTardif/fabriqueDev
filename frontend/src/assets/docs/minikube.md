Kubernetes
===================

Disclaimer : EXPERIMENTAL !!!

We are going to use Minikube to try out Kubernetes locally :
https://github.com/kubernetes/minikube


* Install kubectl :

```yml
curl -Lo kubectl https://storage.googleapis.com/kubernetes-release/release/v1.8.0/bin/linux/amd64/kubectl && chmod +x kubectl && sudo mv kubectl /usr/local/bin/ 
```

* Install Minikube :

```yml
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/
```

* Start Minikube :  
We are going to use the `none` driver : Remember, this is NOT recommended in real case scenario.

```yml
minikube start --vm-driver=none
```

* Try it out :

```yml
kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.4 --port=8080
kubectl expose deployment hello-minikube --type=NodePort
```

Wait until `kubectl get pod` returns a running status :

```yml
NAME                              READY     STATUS    RESTARTS   AGE
hello-minikube-3383150820-vctvh   1/1       Running   0          13s
```

Then :

```yml 
curl $(minikube service hello-minikube --url)
```

Cleaning up :

```yml
kubectl delete service hello-minikube
kubectl delete deployment hello-minikube
```

* Install Kompose :  
(http://kompose.io/)

```yml
curl -L https://github.com/kubernetes/kompose/releases/download/v1.7.0/kompose-linux-amd64 -o kompose

chmod +x kompose
sudo mv ./kompose /usr/local/bin/kompose
```

* Remove from your `docker-compose.yml` everything that is not part of your fabriq
* Run :

```yml 
kompose up
```
And wait until `kubectl get pod` are Running

* Check out what have bee done :

```yml 
kubectl get deployment
kubectl get svc
kubectl get pods
kubectl get pvc
docker ps
```
And your WebApps.

* Once your are done, Clean Up :

```yml 
kompose down
minikube stop

```
