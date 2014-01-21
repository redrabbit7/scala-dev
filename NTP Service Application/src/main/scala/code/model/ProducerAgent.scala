package code.model

import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Cancellable
import akka.pattern.ask
import akka.util.Timeout
import akka.util.Timeout
import scala.concurrent.Future


class ProducerAgent(nrOfConsumers: Int) extends Actor {
    val system = akka.actor.ActorSystem("system")
    //var consumers = Set.empty[ActorRef]
    import system.dispatcher
    val TIME_INTERVAL = 1000
    
    def receive = {
      case Messages.KeepAlive(identity) =>
        println("** KEEP ALIVE Consumer "+identity)
        
      case Messages.Register(consumer,identity) =>
        
        println("Registered consumer "+identity)
	    //consumers += consumer
	    
	    system.scheduler.schedule(0 milliseconds, 1000 milliseconds, consumer, Messages.Time(System.currentTimeMillis))
	    
	    //schedule time message to be sent to consumer
	    /*system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {
          val future = consumer ? Messages.Time(System.currentTimeMillis)
          //, consumer, Messages.Time(System.currentTimeMillis))
        }*/
        
        //schedule time message to be sent to consumer
	    
	    //if(consumers.size == nrOfConsumers){
	      //create router 
	      //Iterable<ActorRef> routees = Arrays.asList(new ActorRef[] { actor1, actor2, actor3 });
	      //ActorRef router2 = system.actorOf(new Props(ExampleActor.class).withRouter(RoundRobinRouter.create(routees)));
	    //}
	    
    }
    

}