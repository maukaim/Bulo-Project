## Re-Architecture project - March 2023  
________________
The current architecture has 2 central components : 1) Orchestrator service and 2) Executors    

<img src="assets/architecture/current_architecture.png" width="800"/>  

It does not completely fill the theoretical definition of an **event based** system definition.
Event should be considered as facts or notifications. It should not look like a RPC like system.
As of today, Orchestrator send a "ExecutionNeeded" event to Executors. even if it does it in a "fire and forget" fashion,
the underlying assumption is that an Executor will, indeed, "reply" something by creating a "ExecutionReport" event.
In future, it will be even worse. Orchestrator will populate ExecutionNeeded, but only some Executor would be able to pick it.  
That looks more like a RPC design.

### Current Design Flaw explained 
_______________

The biggest flaw is that Orchestrator is responsible of 2 major items: FlowRuns and StageRuns.  

The new idea (as of Feb 2023), is to split **Orchestrator** in 2 sub services **FlowRun service** and **StageRun** service. 
The FlowRun service would still be responsible for a Flow execution, and make it evolves depending on the StageRun records received through events.  
The StageRun will inherit from the ExecutionEngine, responsible to compute next StageRun based on the FlowRun received.  
Then, it will resolve which Executor to contact and perform an execution request (Stream style).

The Executors will update the calling StageRun service to provide mid-step updates.