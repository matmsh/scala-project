package net.sf.scalaproject

import akka.actor.{Actor, ActorSystem, Props}

/**
 * Created by shing on 28/04/15.
 */
object Hello {

  def main(args: Array[String]) {
     val system = ActorSystem()

    system.actorOf(Props[Hello]) ! "Hello World!"

    Thread.sleep(1000)

  }

}


class Hello extends Actor {

    def receive = {
      case msg => println("msg:" + msg)
    }
}