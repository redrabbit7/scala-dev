
package code.model

import akka.actor.ActorSystem
import akka.actor.Props

object NTPService extends App {

  //read number of consumers from console
  println("Enter number of consumers > ");
  val numConsumers = readInt

  val system = ActorSystem("System")
  val producerAgent = system.actorOf(Props(new ProducerAgent(numConsumers)), name = "producerAgent")

  for (a <- 1 to numConsumers) {
    //generate random MAX number of messages for the next consumer
    val rnd = new scala.util.Random
    val range = 1 to 12
    val MAX_MESSAGES_SENT = range(rnd.nextInt(1 to 12 length))

    //create actor with ID of a
    val consumer = system.actorOf(Props(new Consumer(producerAgent, MAX_MESSAGES_SENT, a)))

  }

} 