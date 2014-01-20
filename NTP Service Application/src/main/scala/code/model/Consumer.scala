//consumer class, created with reference to Actor
package code.model

import akka.actor.Actor
import akka.actor.ActorRef

  class Consumer(producerAgent: ActorRef, maxMessagesSent: Int, ID: Int) extends Actor {
    val TIME_INTERVAL = 5000
    
    registerSelf
    keepMeAlive

    def registerSelf = {
	  producerAgent ! Messages.Register(self,ID)
	  Thread.sleep(TIME_INTERVAL)
	}
    
    def keepMeAlive = {
      
      for(a <- 1 to maxMessagesSent){
    		producerAgent ! Messages.KeepAlive(ID)
    		Thread.sleep(TIME_INTERVAL)
      }
      //context.stop(self)
    }

    def receive = {
      case Messages.Time(time) =>
        println("Consumer "+ID+" received Time of "+time)
    }
  }