package code.model

import akka.actor.ActorRef
import akka.actor.Cancellable

  object Messages {
    
    case class KeepAlive(ID: Int,keepAliveCount: Int)
    case class Time(systemTimeMillis: Long)
    case class Register(consumer: ActorRef,ID: Int)
  }