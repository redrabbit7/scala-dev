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
    val system = context.system
    //var consumers = Set.empty[ActorRef]
    import system.dispatcher
    val TIME_INTERVAL = 1000
    
    def receive = {
      case Messages.KeepAlive(identity) =>
        println("** KEEP ALIVE Consumer "+identity)
        
      case Messages.Register(consumer,identity) =>
        
        println("Registered consumer "+identity)
	    //consumers += consumer
	    
	    system.scheduler.schedule(0 milliseconds, 1000 milliseconds){
          consumer ! Messages.Time(System.currentTimeMillis)
        }
	    
    }
    

}