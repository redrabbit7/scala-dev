package code.model

import akka.util.Timeout
import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinRouter
import akka.routing.Broadcast
import akka.actor.ActorRef
import akka.routing.DefaultResizer
import akka.routing.Router

class ProducerAgent(nrOfConsumers: Int) extends Actor {
    
    var consumers = Set.empty[ActorRef]
    sendTime
	checkConsumerActivity
    
	//send current time every second to all live consumers
    def sendTime = {
      consumers foreach (_ ! Messages.Time(System.currentTimeMillis))
      Thread sleep 1000
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
    }
    
    
  }