//consumer class, created with reference to Actor
package code.model

import akka.actor.Actor
import akka.actor.ActorRef
import scala.concurrent.duration._

  class Consumer(producerAgent: ActorRef, maxMessagesSent: Int, ID: Int) extends Actor {
    val TIME_INTERVAL = 5000
    import system.dispatcher
    val system = akka.actor.ActorSystem("system")
    val keepAliveCounter : Int = 0
    
    registerSelf

    def registerSelf = {
	  system.scheduler.scheduleOnce(0 milliseconds, producerAgent, Messages.Register(self,ID))
	  
	  keepMeAlive
	}
    
    def keepMeAlive = {
      
      for (a <- 1 to maxMessagesSent){
        system.scheduler.scheduleOnce(TIME_INTERVAL * a milliseconds , producerAgent, Messages.KeepAlive(ID))
      }
      system.scheduler.scheduleOnce(TIME_INTERVAL * maxMessagesSent milliseconds){
        context.stop(self)
      }
    }

    def receive = {
      case Messages.Time(time) =>
        println("Consumer "+ID+" received Time of "+time)
    }
  }