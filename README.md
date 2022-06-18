# Miquido_project
Project was developed at the demand of <a href="https://www.miquido.com">Miquido</a> for a recruitment reasons.

## Table of contents
1. [ Introduction. ](#intro)
2. [ Technologies used ](#technologies)
3. [ Endpoints ](#endpoints)


<a name="intro"></a>
## 1. Introduction
Target of this project was to create a REST API in layered architecture that contains following functionalities:
* create an endpoint to import a character from <a href="https://swapi.dev">Swapi</a> by id into the database
* create an endpoint to retrieve single character by its id
* create an endpoint to search characters by their names, it should allow to search by part of the name and also be case insensitive
* create a validator that filters characters that are shorter than value specified in application.properties
<a name="desc"></a>

<a name="technologies"></a>
## 2. Technologies used
* Java
* Spring
* SwaggerUI
* Project lombok
* PostgresSQL

<a name=endpoints></a>
## 3. Endpoints
**GET** localhost:8080/character/get/{id} - Returns character stored in database by its unique id <br><br>
**POST** localhost:8080/character/import/{id} - Performs HTTP request to **https://swapi.dev/api/people/{id}/** if the character is not shorter than character.minimum specified in application properties, then the character is being added to database.
If the character with that id already exists in database, then it is being updated.<br><br>
**GET** localhost:8080/character/search - requires parameter **name**, searches characters whose name contains specified string - case insensitive. Returns all the characters that fullfill the requirements.

