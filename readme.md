## Reading Is Good

### Tech Stack
*Language:* Java 11  
*Framework:* Spring Framework  
*Database:* MongoDB

## Installation

```sh
run init.sh
```
Please make sure that Maven is installed on you computer. Application will start in 8080 port. 

*Default User:*   
*email:* unclebob@gmail.com </br>
*password:* 1234

[Postman Collection](ReadingIsGood.postman_collection.json) </br>
[Swagger](http://localhost:8080)

You need to authenticate to get token before calling APIs.

```http
curl -X POST "http://localhost:8080/authenticate" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"1234\", \"username\": \"unclebob@gmail.com\"}"
```
