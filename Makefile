
docker-build:
	docker build -t mutantes:latest --build-arg JAR_FILE=target/mutantes.jar .

docker-run:
	docker-compose up

build:
	mvn clean package site
	if [[ -d "site" ]]; then rm -rf site; fi
	cp -r target/site .

deploy:
	mvn appengine:deploy

build-deploy: build deploy

full-build: build docker-build
