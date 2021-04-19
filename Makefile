

build:
	mvn clean package site
	if [[ -d "site" ]]; then rm -rf site; fi
	cp -r target/site .
