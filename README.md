# Technical task - Jan Jakubowski

This repository contains my solution to a technical task for Luxoft company.

My solution includes:
- source code of application
- unit tests
- resource files used by tests

Request examples:
- request to save correct file
```
 curl -i --location --request POST 'http://localhost:8080/api/records' --form 'file=@./src/test/resources/test-file.txt'
```

- request to save file that does not match requirements
```
curl -i --location --request POST 'http://localhost:8080/api/records' --form 'file=@./src/test/resources/test-file-incorrect-regex.txt'
```
- request to save file with incorrect date
```
curl -i --location --request POST 'http://localhost:8080/api/records' --form 'file=@./src/test/resources/test-file-incorrect-date.txt'
```
- request to get existing record by primary key
```
curl -i --location --request GET 'http://localhost:8080/api/records/asdf'
```
- request to get not existing record by primary key
```
curl -i --location --request GET 'http://localhost:8080/api/records/notexisting'
```
- request to get all records
```
curl -i --location --request GET 'http://localhost:8080/api/records'
```
- request to delete existing record 
```
curl -i --location --request DELETE 'http://localhost:8080/api/records/asdf'
```
- request to delete not existing record
```
curl -i --location --request DELETE 'http://localhost:8080/api/records/notexisting'
```

## Stack:

 - Spring Boot
 - H2 database


