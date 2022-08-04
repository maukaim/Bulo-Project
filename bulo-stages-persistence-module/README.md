## Stages Service - Persistence module  

Usage dedicated to Stages Micro-service in a distributed system version of Bulo, this module focus on : 
- Cache management
- Interaction with Publisher to handle persistence in a system with multiple instance of service
- Persistence into database.

As the Serialization-module, it is designed to work with app module but is separated as its purpose is very oriented on data persistence. 

WHO IMPLEMENTS DATASTORE?