
docker-build:
	mvn clean package -DskipTests=true -Pdocker
	docker build -t mutantes:latest --build-arg JAR_FILE=target/mutantes.jar .

run-docker: docker-build
	docker-compose up

run-local:
	docker-compose up -d mongo
	mvn clean package -DskipTests=true -Plocal
	java -jar target/mutantes.jar

build:
	mvn clean package site -Pcloud
	if [ -d ./site ]; then rm -rf site; fi
	cp -r target/site .

deploy:
	mvn appengine:deploy

build-deploy: build deploy

full-build: build docker-build
