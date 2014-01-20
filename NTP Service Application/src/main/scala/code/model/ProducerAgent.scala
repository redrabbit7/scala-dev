package code.model

import akka.util.Timeout
import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinRouter
import akka.routing.Broadcast
import akka.actor.ActorRef
import akka.routing.DefaultResizer
import akka.routing.Router
import scala.concurrent.duration._
import akka.actor.Cancellable
import java.util.concurrent.TimeUnit


class ProducerAgent(nrOfConsumers: Int) extends Actor {
    val system = akka.actor.ActorSystem("system")
    var consumers = Set.empty[ActorRef]
    var count = Int
    import system.dispatcher
    
	//send current time every second to all live consumers
    def sendTime = {
      while(1==1){
	      consumers foreach (_ ! Messages.Time(System.currentTimeMillis))
	      Thread sleep 1000
      }
    }
    


    //check each Consumer every 10 seconds for activity
    def checkConsumerActivity = {
      //Timeout timeout = new Timeout(Duration.parse("5 seconds"));
      //Future<Object> future = Patterns.ask(actor, msg, timeout);
      //String result = (String) Await.result(future, timeout.duration());
    }

    def receive = {
      case Messages.KeepAlive(identity) =>
        println("Keep alive message received from Consumer "+identity)
        
      case Messages.Register(consumer,identity) =>
        
        println("Registered consumer "+identity)
	    consumers += consumer
	    
	    if (consumers.size == nrOfConsumers){
	      println("All consumers are registered...")
	      //consumers foreach (_ ! Messages.Time(System.currentTimeMillis))
	      
	      system.scheduler.scheduleOnce(1000 milliseconds, consumer, Messages.Time(System.currentTimeMillis))
	      //checkConsumerActivity
	    }
	    
    }
    
    
    
  }