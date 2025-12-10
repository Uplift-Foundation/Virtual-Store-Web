# Virtual Store Web
## To build and run your app in docker:

1.  Run the following docker command for postgres:

        docker run --name virtualstore-web-db -p 5432:5432 -e POSTGRES_PASSWORD=YOURDBPASSWORD -e POSTGRES_USER=YOURDBUSERNAME -e POSTGRES_DB=virtualstore-web-db -d postgres

2.  Run the following docker command for pg4admin:

        docker run -p 81:80 -e 'PGADMIN_DEFAULT_EMAIL=youremail@email.com' -e 'PGADMIN_DEFAULT_PASSWORD=yourpgadminpassword' -d dpage/pgadmin4:snapshot

3.  Browser to localhost:81/browser/ and login.  Add a server, using your ip address as the host and the login information from the first step

4.  Make sure you have the following in your application.properties:

        server.port=80
        spring.jpa.database=POSTGRESQL
        spring.datasource.url=jdbc:postgresql://localhost:5432/virtualstore-web-db
        spring.jpa.show-sql=true
        spring.jpa.generate-ddl=true
        spring.jpa.hibernate.ddl-auto=create-drop
        spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
        spring.profiles.active=@active.profile@
        spring.web.resources.cache.cachecontrol.no-cache=true
        spring.web.resources.chain.cache=false
        spring.thymeleaf.cache=false
        spring.cache.type=NONE

5.  Make sure you have the following in your application-YOURNAME.properties, matching the username/password from step 1:

        spring.datasource.url=jdbc:postgresql://YOURIPADDRESS:5432/virtualstore-web-db
        spring.datasource.username=YOURDBUSERNAME
        spring.datasource.password=YOURDBPASSWORD
        spring.jpa.hibernate.ddl-auto=create-drop
        spring.web.resources.cache.cachecontrol.no-cache=true
        spring.web.resources.chain.cache=false
        spring.thymeleaf.cache=false
        spring.cache.type=NONE

6. In your pom.xml file, add a profile to your profiles node:

        <profiles>
            <profile>
                <id>YOURNAME</id>
                <properties>
                    <active.profile>YOURNAME</active.profile>
                </properties>
            </profile>
        </profiles>

7.  In the .vscode directory - there is a folder called .vscode/launch.json.  It's contents should be this:

        {
            "configurations": [
                {
                    "type": "java",
                    "name": "Launch Virtual Store Web",
                    "request": "launch",
                    "cwd": "${workspaceFolder}",
                    "console": "internalConsole",
                    "mainClass": "com.upliftfoundation.virtualstoreweb.VirtualStoreWebApplication",
                    "projectName": "virtualstore",
                    "args": "--spring.profiles.active=YOURNAME"
                },
                {
                        "type": "java",
                        "name": "Launch with Cloud Database",
                        "request": "launch",
                        "cwd": "${workspaceFolder}",
                        "console": "internalConsole",
                        "mainClass": "com.upliftfoundation.virtualstoreweb.VirtualStoreWebApplication",
                        "projectName": "virtualstore",
                        "args": "--spring.profiles.active=docker"
                }
            ]
        }

8.  Open your .gitignore file and make sure application-YOURNAME.properties is added to the bottom, or else you might end up checking in your properties file and sharing your password with everyone like Makayla did:

        application-warren.properties
        application-sarai.properties

9.  Create a Dockerfile if it doesn't exist, and make sure the Dockerfile contains the following:

        FROM maven:3.8.1-openjdk-11-slim AS build
        ARG PROFILE
        RUN mkdir -p /workspace
        WORKDIR /workspace
        COPY pom.xml /workspace
        COPY src /workspace/src
        RUN --mount=type=cache,target=/root/.m2 mvn -B clean package --file pom.xml -P $PROFILE

        FROM openjdk:11-slim
        COPY --from=build /workspace/target/*jar app.jar
        EXPOSE 80
        ENTRYPOINT [ "java", "-jar", "app.jar" ]

10.  You may now build your image - passing in the profile to use as a build argument:

            docker build --build-arg PROFILE=YOURNAME -t virtualstoreweb .

11.  Finally, run the container using the following command:

            docker run --name virtualstoreweb -p 80:80 -d virtualstoreweb