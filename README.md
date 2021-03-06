#Gcloud Initialization
gcloud init
gcloud --help
gcloud auth list
gcloud config list
gcloud info
gcloud component list
gcloud auth application-default login

#Create Kubernetes cluster
gcloud compute zones list
gcloud config set compute/zone us-central1-a
gcloud container clusters create xaracloud-microservices --no-enable-cloud-logging
gcloud container clusters describe xaracloud-microservices
#Enable kubectl and proxy
gcloud container clusters get-credentials xaracloud-microservices --zone us-central1-a --project hack-gcph-7
kubectl proxy

#Create and upload image to GCR
Docker on cmd enable : docker-machine env --shell cmd default
docker build -t customer-service:v1 .
docker build -t account-service:v1 .
docker build -t customer-service:v2 .
docker run -d -p 8080:3333 customer-service:v1
# tag the image
docker tag customer-service:v1 gcr.io/hack-gcph-7/customer-service:v1
docker tag account-service:v1 gcr.io/hack-gcph-7/account-service:v1
docker tag customer-service:v2 gcr.io/hack-gcph-7/customer-service:v2
# push the image
gcloud docker -- push gcr.io/hack-gcph-7/customer-service:v1
gcloud docker -- push gcr.io/hack-gcph-7/account-service:v1
gcloud docker -- push gcr.io/hack-gcph-7/customer-service:v2

#create Controller and service
kubectl cluster-info
kubectl create -f account-service-rc.yaml
kubectl get rc
kubectl get pods -o wide
gcloud compute ssh gke-xaracloud-microservi-default-pool-ef28332c-xcc2
     sudo docker ps
	 exit
kubectl create -f customer-service-rc-v1.yaml
kubectl create -f account-service.yaml
kubectl create -f customer-service.yaml


######DNS Resolve############
kubectl get pods -o wide
gcloud compute ssh gke-xaracloud-microservi-default-pool-ef28332c-xcc2
	sudo docker ps
	sudo docker exec -it d08f4f3b039e bash
	nslookup account-service
	exit
	exit

	http://localhost:8001/api/v1/proxy/namespaces/kube-system/services/kibana-logging/