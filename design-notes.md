# How to Run
* Open in an editor of choice
* Create a simple application.yaml file in the resources folder to configure the h2database

Sample:

spring:
    datasource:
        url: jdbc:h2:mem:testdb
        driver-class-name: org.h2.Driver
        username: sa
        password:
    jpa:
        hibernate:
            ddl-auto: create-drop
        show-sql: true
        database-platform: org.hibernate.dialect.H2Dialect


* If an editor with built in java compiler then simply run project
* Otherwise using maven:

    1. mvn clean install
    2. java -jar target/PawneeParksAndRecreation-0.0.1-SNAPSHOT.jar


## The Customer Fields and why
    I decided to go with simple indentifying attributes for a customer. The two more unique fields 
    (last contacted and customerType) were added with future upgrades in mind.

### Customer
    Customer type is an enum that represents the different types/classification of customers the
    company has to interact with. By making it an enum it can easily be validated and the number
    of different types can be expanded/reduced with general ease.

### lastContacted
    Based on the optional feature description I forsee the need for a way to determine the amount
    of time has passed since the last interaction with a customer. As such I included lastContacted
    to keep track of this value whenever an action is taken between the customer and the company.
    For example if the system in the future is expanded to send out email then this value can be
    updated upon sending that email to keep track of last correspondence.

    I decided to go with LocalDate for its ease of setting an initial value as well as calculating
    the period between the interactions by comparing the current date to the last contacted date.

## API Design
    When considering which design pattern to use I quickly landed on using MVC (Model View Controller).
* MVC with facilitate scalability for future upgrades but did take time to properly implement that slowed
  down my solution delivery.
* MVC allows for Domain Driven Design (DDD) where you can clearly define models, entities and other objects
  that are deeply related to key business concepts
* Allows for easy and effective testing of Services and Controllers to ensure they are fully functional


## Testing, Error Handling and Extensibility
    I am an advocate for test driven development. That is I create test before focusing on functional logic.
    This avoids a common mistake of writing test speifically around the already established logic you have 
    developed. As such I wrote simple stubs for my service methods first, completed making test for them and
    then went back to complete the logic for those stubs.

    All methods are adequately error handled and logged within the api level. This ensures that even if an
    unexpected error occures it does not crash the program but rather notify that some functionality failed
    to execute. Due to a lack of time I did not write Validation classes (which are cleaner) but instead
    leveraged the built in annotations for validating the different class attributes.

    As mentioned above MVC's struture inherently helps with taking extensibility into account. As for my own
    considerations I ensured that while developing the methods were loosely coupled so that if any future
    changes have to be made to one it wont affect multiple others that are dependent on it. Likewise I have
    3 different models that relate to a customer. Each with varying characteristics but different usecases
    which makes it easy to refactor without having to modify a large amount of code
## Shortcuts or Assumptions
    It was assumed that even though it was said no database should be used that an h2 in memory database would be okay
    since it does not persist data between sessions.

    Leveraging lombok to cut out the need to create boiler code for all the models in the project greatly increased
    the speed of development

    Using the Spring Initializr to configure my project(not sure if this can be considered a shortcut though)
## The Scaling Question
    In my previousresponses, scalability has come out quite a bit. If I were change the application to scale it up
    to handle millions of customers I would:

1. Use an actual database instead of using an in memory one. This will allow the system to store
   the increased volume. Also I would implement indexing on the customer table to optimize speed.
2. Deploy the service across multiple pods/instances using a tool like kubernetes (Horizontal
   Scaling).
3. Since the system is somewhat basic in functionality, applying async the non-critical methods.
   This can help with managing the increased load on the system by decoupling of time-intensive
   operations.