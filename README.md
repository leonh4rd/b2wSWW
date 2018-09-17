# b2wSWW
This Java API allows you to create, retrieve and delete planet information of the fictional universe of Star Wars in a local database.
Its still under development, but some functionalities can be used.

## Getting Started
Here some instructions to setup your local system for running the project

## Prerequisites

## Built with
**Eclipse JavaEE**  
**Maven** - dependency management.  
**Jersey framwork** - for a REST API.  
**Apache Tomcat 9** - server for running the project servlets.  
**MongoDB** - previously created SWU database with a planets collection.  
**Postman** - for runnning the http requests such as GET, POST and DELETE.  

## Running
* _To insert a planet in the database:_  
On Postman, select the **POST** request and insert as address http://localhost:8080/rest/sw/planets/insert  
In the Headers, set the "Content-Type" as "application/xml"  
In the body, insert the xml structure for the world to add in the database, for example:  
```
<world>  
  <name>Endor</name>  
  <climate>Temperate</climate>  
  <terrain>Mountains</terrain>    
</world>    
```
And send the information and the entry with Endor will be created on your local database.  

* _To get a specific planet information:_  
On Postman, select the **GET** request, insert the address http://localhost:8080/rest/sw/planets/{name}, as {name} being the name of the planet you want the information.  

* _To get a list of all the planets in the database:_  
On Postman, select the **GET** request, insert the address http://localhost:8080/rest/sw/planets/  

* _To get the first planets in the database:_  
On Postman, select the **GET** request, insert the address http://localhost:8080/rest/sw/planets/1  

* _To delete a specific planet in the database:_  
On Postman, select the **DELETE** request, insert the address http://localhost:8080/rest/sw/planets/{name} as {name} being the planet you want to remove from the database.  

## Knows issues  
* On a POST request, its trying to get data from the https://swapi.co/api but the response is not what is intended;  
* Setup only for local database only;  
* No verification of already existing planets in the database when inserting a new one;  
* No verification of existance of planet when trying to delete it;  
* ID field of the planets not working as intended;  




