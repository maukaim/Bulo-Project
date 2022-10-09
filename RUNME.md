# Getting Started  
First you need to download and set up the mulit-modules project:
```shell
git clone git@github.com:maukaim/Bulo-Project.git
cd Bulo-Project
git submodule init
git submodule update
```  

Once done, run a maven install to upload the dependencies and build the required artifacts. From your IDE maven plugin, or from cmd line: 
```shell
mvn clean install
```  

You are now ready to run the application.
## Standalone App (Alpha version)

Simply launch the app from the main method in _standalone/standalone-app/.../BuloStandaloneApplication.java_. 
An uber-jar will be available in the Beta version so the app could run from anywhere.


## Microservices App (Alpha version) 

As simple as the Standalone version, you just need to run the Main class for each microservice.
If using IntelliJ, you may already have them listed in your *Services* tab, at the bottom of the window.  
If not, run the microservices manually. You can find the main classes in **microservices/bulo-<module_name>-microservice/bulo-<module_name>-app** folders.

You need to launch ALL-except the executors module. Wait for other components to finish start up process, and then launch executors module.

Why this process? It's a temporary solution In Alpha version. For the moment, no persistence module, and Kafka is not yet implemented to make async communication between services. 
Connection between services is performed with REST calls by temporary helper classes in ```bulo-ms-connectiy-module```. Therefore,
you need to respect a certain order when launching the services. This requirement will disappear with Kafka, in Beta version.