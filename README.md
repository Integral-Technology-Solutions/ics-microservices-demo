# microservice-demo


###### This project uses graphql to handle the CRUD operations for the


#### GraphQL UI

     http://localhost:8080/graphiql

#### GraphQL API Documentation

###### Go to the GraphQL UI above and click the Docs link and you will be able to see the API documentation


#### Using GraphQL to create, update, read and delete the data   

###### Create new user

        mutation {
          createUser(userInput: {name: "test", email: "test@gmail.com", active: true}) {
            id
            name
            email
            active
          }
        }

###### Update a user

        mutation {
          updateUser(userInput: {id: "e4f250a6-7277-429c-8c94-eb602886e859", name: "update", email: "test@gmail.com", active: true}) {
            id
            name
            email
            active
          }
        }

###### Update users

        mutation {
          updateUsers(userInputs: [{id: "60c3f323-0d9b-4a8d-8300-da80d8fa1920", name: "update", email: "test@gmail.com", active: true},
            {id: "d86c4eb0-aaa8-4d1c-be49-46196f3bb126", name: "update", email: "test@gmail.com", active: true}]) {
            id
            name
            email
            active
          }
        }

###### Delete a user

        mutation {
          deleteUser(id: "e4f250a6-7277-429c-8c94-eb602886e859") {
            id
            name
            email
            active
          }
        }

###### Delete users

        mutation {
          deleteUsers(ids: ["83a159d1-ac9f-418e-ae06-a41d86e14651", "2ed42b3b-f960-4d2c-97f1-fe43fe17ba2d"]) {
            id
            name
            email
            active
          }
        }

###### Get a user

        {
          getUser(id: "e4f250a6-7277-429c-8c94-eb602886e859") {
            id
            name
            active
            email
          }
        }

###### Get all users

        {
          getUsers {
            id
            name
            active
            email
          }
        }

###### Build the application  

    mvn clean install      

###### Build and Run the application using Docker

    cd config/docker/
    build.sh

###### Run the application

    mvn jetty:run

###### Health check for the application using

    http://localhost:8080/actuator/health

###### Access H2 Console

    http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:mem:testdb
    Username: sa
    Password: <leave this empty>
