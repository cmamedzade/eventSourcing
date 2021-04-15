import Handler.{ CreateAccount}
import akka.actor.typed.{ ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.persistence.typed.PersistenceId
import akka.util.Timeout
import akka.actor.typed.scaladsl.AskPattern._
import scala.language.postfixOps
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Initiative {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(10 seconds)
    val guardian = Behaviors.setup[String]{
      context =>
        implicit val system = context.system
        val actor = context.spawn(Ask(system,timeout),"my-actor")
        Behaviors.empty[String]
    }
    implicit val stm = ActorSystem(guardian, "System")
  }
}

object Ask{
  def apply(implicit system: ActorSystem[_], timeout: Timeout): Behavior[String] = {
    implicit val ex = system.executionContext
    Behaviors.setup[String] {
      (context) =>
        val actor = context.spawn(Handler("abc", PersistenceId.ofUniqueId("abc")), "my-actor")
        actor.ask[String](ref => CreateAccount(ref)).onComplete {
          case Success(value) => println(value)
          case Failure(exception) => println(exception)
        }
        Behaviors.empty[String]
    }
  }
}
