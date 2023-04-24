## Eventual Consistency for RunService && ExecutorService

The idea is that RunServices and Executor should scale, they are the 2 main bottleneck
of Bulo as they are heavy task oriented.   

### RunService (old. Orchestrator)  

The RunService should scale as we want, but how? The main challenge is that when an event
occur in ExecutorService, we are sure that the RunService who KNOWS about the FlowRun is the one
who receives the update, because eventual consistency is NOT OK to accept here.

Kafka mode: We should use a "MainRunContextId" key for the Executor to send the StageRunUpdate. 
The reason for that, is with this key kafka will ensure, inside the same consumerGroup, that the same
instance receives the update for this key. The instance of RunService who receives the update, is ensured
to already know everything about the other updates, and the FlowRun in general.
But what if this instance is down? Then the key will be redirected to another instance of Runservice, 
which hopefully knows about the details of the FlowRun (Eventual Consistency), better than nothing.

REST mode: Once the Executor needs to update the RunService, it will send a REST request to the
list of instances DECLARED of RunService. If the RunService recognizes being the owner of the FlowRun, it accepts. 
Otherwise refuses.


#### Another problem for RunService  

What if Multiple instances, Kafka. Problem of consistency when a FlowRun has to proceed to next step.
We should ensure that only 1 instance of RunService tries to update a FlowRun, so StageRunUpdate Events should
come with Keys, so we are sure same instance will proceed each FlowRun.
Should add a version number? 
So the SAVE operation for the instance modifying the FlowRun will fail if version is not superior.
In REST mode, we will still be accurate so no problem.

### ExecutorService  

The ExecutorServices have different Stages they are able to support. 
The DefinitionService keep track of it. A "StageRunRouterService" should rely on this info from DefinitionService and StageService to 
get the list of Executor able to do a Stage. It will then decide to apply a load balancing algorithm to contact directly
one of them, and ask to execute. Once it accepts, StageRunRouterService send the "Acknowledged" update to the RunService.
The executor will then have to send its update, cf. RunService section.
PS: The "ReadyToRunEvent" should now include the "MainRunContextId" so the updates will be sent under this key.

Kafka: publish the ReadyToRunEvent" to topic, and "StageRunRouterService" instances 