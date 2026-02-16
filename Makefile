PROFILE ?= local
DEBUG_PORT ?= 5005
CONTAINER_CLI ?= docker-compose
MVN_CANONE ?= $(CONTAINER_CLI) exec jdk mvn

run:
	$(MVN_CANONE) -q spring-boot:run -Dspring-boot.run.profiles=$(PROFILE) -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${DEBUG_PORT}"

fmt:
	$(MVN_CANONE) -q test

test:
	$(MVN_CANONE) -q test

migrate:
	$(MVN_CANONE) -q flyway:migrate

build:
	$(MVN_CANONE) -q -DskipTests=false verify

install:
	$(MVN_CANONE) -q clean install

clean:
	$(MVN_CANONE) -q clean

package:
	$(MVN_CANONE) -q -DskipTests package

up:
	$(CONTAINER_CLI) up -d

down:
	$(CONTAINER_CLI) down

sh:
	$(CONTAINER_CLI) exec jdk bash

