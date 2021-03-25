# CDDBAPI

This application is designed for use in DevOps and SRE training.

The application is a simple CD Database application using Spring Boot with a MySQL Database backend.  The current state as of March 2021 is that there is an API server and MySQL database.  The API allows for;
* Full listing
* Listing a specific CD by id
* Adding CDs
* Deleting CDs

A Jenkinsfile is supplied to enable the use of pipelines through Jenkins Blue Ocean, where by providing this GIT repo will automatically build the pipeline.

## Setting up Jenkins

For Jenkins to understand the pipeline you will need to add the following plugins and server configurations.

### Plugins and configurations

Plugins:

* Blue Ocean

Configurations:


## The API

Listing all CDs
```
$ curl -H 'Content-Type: application/json' localhost:8080/api/compactdiscs
```

Adding a CD without tracks;
```
$ curl -X POST -H 'Content-Type: application/json' localhost:8080/api/compactdiscs -d '{
"title": "This is Steve",
"artists": "Me",
"price": 12.99
}'
```
