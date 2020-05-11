# Description

This is a demo application for me to get used to using SpringBoot for creating and consing REST APIs. 
The aim is to create a web application similar to the mobile application I developed [here](https://github.com/SedaKunda/android-departureboard-mvvm).

This application queries an external API, saves the returned data in an internal repository and then exposes endpoints for that repository that can in turn be queried.

Data is obtained from the [TransportAPI](https://www.transportapi.com/) 

# Technologies
- Java
- SpringBoot
- Maven
- Junit
- Mockito
- TDD approach
- Maven

# Setup
- Generate your own APP_ID and APP_KEY from [TransportAPI](https://www.transportapi.com/).
- Create a keys.properties file in the resources folder
- Add the APP_ID={YOUR_APP_ID} and APP_KEY={YOUR_APP_KEY} lines in the keys.properties

# Running
If using Maven, run with ./mvnw spring-boot:run

# Note
Some of the endpoints are in alpha testing and are subject to change.