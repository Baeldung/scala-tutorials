trait Encryptor[A] {
  def encrypt(value: A): String
}


case class Password(value: String)

object EncryptorInstances {
  implicit val passwordEncryptor: Encryptor[Password] =
    new Encryptor[Password] {
      def encrypt(value: Password): String =
        s"encrypted{$value}"
    }
}

object Encryption {
  def toEncrypted[A](value: A)(implicit e: Encryptor[A]): String =
    e.encrypt(value)
}

import EncryptorInstances._ // import all the instances

Encryption.toEncrypted(Password("abc123"))

// encrypted{abc123}


object EncryptionSyntax {

  implicit class EncryptorOps[A](value: A) {
    def toEncrypted(implicit e: Encryptor[A]): String =
      e.encrypt(value)
  }

}


import EncryptorInstances._
import EncryptionSyntax._

Password("1234").toEncrypted
// encrypted{abc123}