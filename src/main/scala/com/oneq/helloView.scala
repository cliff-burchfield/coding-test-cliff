package com.oneq
import com.oneq.model.User

object HelloView{
  def renderUser(user: User): String = {

    return "<html>" + 
            "<body>" +
             s"<h1>Hello, ${user.name}</h1>" +
              s"<p>${user.id}</p>" +
            "</body>" +
          "</html>"
  }
}

