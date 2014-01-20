//consumer class, created with reference to Actor
package code.model

import akka.actor.Actor
import akka.actor.ActorRef
import scala.concurrent.duration._
import akka.actor.Cancellable

  class Consumer(producerAgent: ActorRef, maxMessagesSent: Int, ID: Int) extends Actor {
    val TIME_INTERVAL = 5000
    import system.dispatcher
    val system = akka.actor.ActorSystem("system")
    
    registerSelf
    keepMeAlive

    def registerSelf = {
	  system.scheduler.scheduleOnce(TIME_INTERVAL milliseconds, producerAgent, Messages.Register(self,ID))
	}
    
    def keepMeAlive = {
      system.scheduler.schedule(0 milliseconds,TIME_INTERVAL milliseconds, producerAgent, Messages.KeepAlive(ID))

    }

    def receive = {
      case Messages.Time(time) =>
        println("Consumer "+ID+" received Time of "+time)
    }
  }