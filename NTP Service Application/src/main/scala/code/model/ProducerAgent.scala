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


class ProducerAgent(nrOfConsumers: Int) extends Actor {
    val system = akka.actor.ActorSystem("system")
    var consumers = Set.empty[ActorRef]
    var count = Int
    import system.dispatcher
    
    //check each Consumer every 10 seconds for activity
    def checkConsumerActivity = {
      //check for keepalive message on each actor every 10 seconds
      
    }

    def receive = {
      case Messages.KeepAlive(identity,keepAliveCounter) =>
        println("Keep alive message received from Consumer "+identity)
        
      case Messages.Register(consumer,identity) =>
        
        println("Registered consumer "+identity)
	    consumers += consumer
	    
	    //schedule time message to be sent to consumer
	    system.scheduler.schedule(0 milliseconds, 1000 milliseconds, consumer, Messages.Time(System.currentTimeMillis))
	    
	    
    }
    

}