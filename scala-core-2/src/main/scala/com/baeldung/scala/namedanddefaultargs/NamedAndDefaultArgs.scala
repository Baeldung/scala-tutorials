package com.baeldung.scala.namedanddefaultargs

import java.time.LocalDateTime

object NamedAndDefaultArgs {

  def prettyPrint[A](input: Array[A],
                     start: String = "(",
                     separator: String = ",",
                     end: String = ")"): String = input.mkString(start, separator, end)

  case class DeliveryOrder(product: String,
                           addressToDeliver: String,
                           amount: Int = 1,
                           promoCode: Option[String] = None,
                           byTime: Option[LocalDateTime] = None,
                           comments: Option[String] = None
                          )

}
