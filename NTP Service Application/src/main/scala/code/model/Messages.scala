package code.model

import akka.actor.ActorRef

  object Messages {
    
    case class KeepAlive(ID: Int)
    case class Time(systemTimeMillis: Long)
    case class Register(consumer: ActorRef,ID: Int)
  }