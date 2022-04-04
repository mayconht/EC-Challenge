#Ecore Code Challenge

## :computer: Getting Started

###Running the project locally:
make sure your JAVA_HOME is working properly with version 17+
> ./mvnw spring-boot-run
http://localhost:8080/roles


````docker build -t ecore/challenge:1.0.0 . ````

````docker run -d -p 8080:8080 -e DATABASE_SERVER=jdbc:h2:mem:testdb ecore/challenge:1.0.0````

or **Simply run docker.sh or docker.bat** depending on your system OS.

>**The endPoint references tests are on folder \Postman and can be imported to Postman**

## :pencil: Approach (Dev Review)
The project's main idea is to integrate Roles to the User endpoint, providing a connection between roles and users.
Also, the system provides the ability to assign a role to an entire team including its team leader.

So the process flow is Role -> User -> Team

The Role process should check if a user exists before assigning it a role. the same will be made to the team's endpoint.

### :wrench: Improvement Points (Dev code Review)
* Improve and create all Test cases using Junit / Cucumber (not able to finish them all)
* Create more exception cases and/or generic exceptions
* Generate UUID using hibernate
* Avoid Using Queries on Repository layer (not an issue, just clean practice)
* Improve the Design factory with Mockito to cover more test cases.

## :pencil: Improvement Points (Test Ecore Review)
* Return the correct HTTP message to the requester
* Use Headers 
* Use Authentication if necessary
* Return error when using incorrect HTTP request method.
* Improve the test information by giving the desired structure workflow (with or without error so the dev can fix it)

### :speech_balloon:
Even Though I was not able to finish the entire test on the time I was provided with (for personal reasons, you can check the GitHub commit timestamp) it was such a great and insightful challenge.
It really made me get to a few limits that I sometimes was not able to think about it.
So I have to say thank you for this opportunity and hope you guys can see this code as a true way to show my knowledge and how much more I can acquire with the right guidance, as I mentioned during the interviews.
