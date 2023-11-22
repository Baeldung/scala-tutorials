package com.baeldung.scala.zio.httpapp.app

import zio.*
import zio.http.*

object IngredientWebsocketApp:

  private val appContext = "ingredients"

  def apply(): Http[Any, Nothing, Request, Response] =

    val socket = Handler.webSocket { channel =>
      channel.receiveAll {
        case ChannelEvent.Read(WebSocketFrame.Text(input)) =>
          channel.send(ChannelEvent.Read(WebSocketFrame.text(input * 2)))
        case _ =>
          ZIO.unit
      }
    }

    Http.collectZIO[Request] {
      case Method.GET -> Root / IngredientWebsocketApp.appContext =>
        socket.toResponse
    }
