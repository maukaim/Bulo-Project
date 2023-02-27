<div>
    <h1 class="title"><img src="docs/assets/bulo-logo.svg" width="100" alt="Bulo logo" /> Bulo Automation Platform (ALPHA)</h1>  
</div>
_____________________________________________________

Bulo Platform is a solution for IT & Operational teams to collaborate and automate their business.   

Create complexe workflows, increase your productivity and avoid coding 2 times the same feature.
Bulo is built as an Open Source Software under the MIT license, and has been designed to be flexible and customizable to fit all company size.

__*Bulo is in Alpha version. Fast iterations until the 1st release in mid 2023, 
the code base and structure may significantly change.  
Please check before engaging in a PR.*__

## Features  

- ### Collaborative
    Bulo is a tool for operational teams to automate their workflows, leveraging on *Stages* built by IT colleagues.  
By collaborating, they increase productivity and reduce time to market. All can manage, maintain and transform existing *Flows*.
- ### Reusable 
    Create a *Stage* once, and use it as many times as you want.  
With Bulo, users only need to build small technical bricks, and assemble them in *Functional Stages* and *Flows* to achieve their goals.
- ### Scalable
    Thanks to its hyper-modular architecture, you can use Bulo in both standalone & microservice mode.  
Test the functionalities on your PC to play around, or deploy the microservices to automate your business. Bulo will adapt to your needs!
- ### Customizable  
    The code structure of Bulo's Backend is designed to be easily customizable. Limits between the conceptual layers of our applications (domain, io, serialization, data, server,...) are well defined.  
It's easy to replace a full layer or implement an interface to replace a single class to fit your company needs.


## Getting Started
_Java 17 (LTS) required_. 

Copy the Java project on your local machine:
```shell
git clone git@github.com:maukaim/Bulo-Project.git
```  

You may already build the microservices with Uber-Jar. Run:
```shell
mvn clean install
```
and use java command line to launch the applications jar (all xxx-app modules). i.e:
```shell
java -jar application-standalone-1.0.0-SNAPSHOT.jar
```

The easiest solution is to use an IDE, it will discover all the apps available in the project and make it runnable for you.
In the current version, no need to install & run Kafka on your machine. 
- Standalone provides a turnkey jar. 
- For microservices, as of Feb 2022, only 1 requirement: launch Executor Service after any other service.

## Contributing

Every one is welcomed ! You can help on the architecture design, build a new Stage Runners or help with documentation. 
If you are interested feel free to reach out : julienelk@gmail.com 
To understand how to make your first contribution to the project, please read [CONTRIBUTE_ME.md](docs/CONTRIBUTEME.md)  


## Roadmap Alpha version - Start of 2023

<img src="docs/assets/architecture/target_architecture.png" width="800" alt="Architecture ALPHA version of Bulo"/>

Prioritized items (as of Feb-March 2023):
- [X] Add Mockingbird module (integration tests)
- [X] Refactor, standardize modules coding style.
- [ ] Add Kafka integration
- [ ] Add Jenkins to start CI/CD pipeline.
- [ ] Add AWS integration.
- [ ] Add Dockers to universalize development.
- [ ] Refactor flaws in current MS Architecture (Orchestrator becomes FlowRun service + StageRun service, see [details here.](docs/Architecture_refacto_project.md))
- [ ] Add Plugin System for Java runners (Copy what exists in my other project, [Moula, module plugin-core](https://github.com/maukaim/prototype-moula-backend-api))
- [ ] Pluggable Logging system (Wrapper API on Logback)  

###
<br/>
<div align="center">
<img src="docs/assets/flow_example_twitter.gif" width="600" alt="A simple flow execution represented. Twitter stages, excel, and send a mail."/>
</div>  
<br/>
