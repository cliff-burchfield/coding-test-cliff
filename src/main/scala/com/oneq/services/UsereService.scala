package com.oneq.services

import com.oneq.model.User

import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import akka.http.scaladsl.server.directives.Credentials

object UserService {
    
    val cache = Map("bob" -> User("Bob Marley", "bob", "3bird"), "jim" -> User("Jimmy Hendrix", "jim", "FoxLady"))

    def getUser(id: String): Future[Option[User]] = {
        Future{
            cache.get(id)
        }
    } 

    def authenticate(creds: Credentials): Option[String] = {

        creds match {
                case p @ Credentials.Provided(id) =>

                        cache.get(id)
                          .filter(u => p.verify(u.password))
                          .map(u=>id)
                case _ => None
        }
    }
}