#!/bin/sh
artifact_name=ics-microservice-demo
artifact_version=0.0.1-SNAPSHOT
docker_image=demo/${artifact_name}

cd ../../
mvn clean install
cd config/docker

rm -fr app.jar
cp ../../target/${artifact_name}*.jar app.jar
docker build -t $docker_image:1.0 .

#Run the docker image locally on dev011
#docker run -it -p 8080:8080 $docker_image:latest

#Stop the docker container
#docker stop <docker_instance_name>

#To clean up all existing docker images
#docker rmi -f `docker images -q`

# These are done by BAMBOO Tasks
#Tag the docker image to prepare for publishing to AWS ECR
#docker tag $docker_image:$artifact_version:latest $docker_registry/$docker_image:latest

#Push the docker image to AWS ECR
#docker push $docker_registry/$docker_image:latest
