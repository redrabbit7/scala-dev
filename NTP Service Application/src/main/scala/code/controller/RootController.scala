package code
package controller

import net.liftweb.http._
import net.liftweb.util._
import java.util.Date
import code.lib._
import Helpers._
import akka.actor.ActorSystem
import akka.actor.Props
import code.model.Consumer
import code.model.ProducerAgent

/**
 * A simple MVC controller.  This controller will intercept
 * the given URL and will transform the return page based on
 * some computed value
 */
object RootController extends MVCHelper {

  // Update the time on the index (home) page
  serve {
    case "index" :: Nil => {
      // replace the contents of the element with id "time" with the date
      "#time *" #> DependencyFactory.inject[Date].map(_.toString)
    }
  }

  // serve a page if and only if the second URL param
  // is an Int
  serve {
    case "show_int" :: AsInt(param) :: Nil =>
      runService(param)
      "#int_value" #> param
      
  }

  def runService(numConsumers: Int) {
    //read number of consumers from console
    //println("Enter number of consumers > ");
    //val numConsumers = readInt

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
}
