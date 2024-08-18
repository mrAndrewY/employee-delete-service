#!/bin/bash
workdir='/home/andrew/projects/employee-delete-service'
image_name='employee-delete-service:v1'
tagged_image="mrandrewy/$image_name"
namespace='shop'
service_name='employee-delete-service'
cd $workdir
mvn clean install
docker rmi -f $image_name
docker build $workdir -t $image_name 
docker tag $image_name $tagged_image
docker push $tagged_image 
kubectl -n $namespace delete deployment $service_name
kubectl -n $namespace apply -f "$workdir/kuber/kuber_config.yaml"
kubectl -n $namespace get pods

