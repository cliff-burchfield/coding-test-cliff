package com.oneq

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import com.oneq.services.UserService

import akka.http.scaladsl.server.Route
import com.oneq.model.User

object AllRoutes{

  val health = (get & path("health")) {
    complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "OK"))
  }

  val userScreen =
    Route.seal {
      pathPrefix("users" / Segment){id=>
        get {
            authenticateBasic("", UserService.authenticate) { userName =>
              onSuccess((UserService.getUser(userName))) {
                case Some(user) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, HelloView.renderUser(user)))
                case None => complete(StatusCodes.NotFound)
              }
            }
        }
      }
    }

  val static = (get & pathPrefix("static")){
    (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
      getFromFile("static/index.html")
    } ~ {
      getFromResourceDirectory("static")
    }
  }

  val root = get { // Default to index page
      redirect("/static/", StatusCodes.TemporaryRedirect)
  }

  def routes = health ~ static ~ userScreen //~ root
}