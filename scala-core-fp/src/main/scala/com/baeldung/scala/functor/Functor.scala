package com.baeldung.scala.functor

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.security.MessageDigest
trait Functor[F[_]] {
//    [1]      [2]
  def fmap[A,B](fa: F[A])(f: A => B): F[B]
//   [3]           [3]     [4]       [5]
}

object Functor {

  val listFunctorInstance: Functor[List] = new Functor[List]{
    override def fmap[A, B](fa: List[A])(f: A => B) = fa map f
  }
  val streamFunctorInstance: Functor[Stream] = new Functor[Stream] {
    override def fmap[A, B](fa: Stream[A])(f: A => B): Stream[B] = fa  map f
  }
  val optionFunctorInstance: Functor[Option] = new Functor[Option] {
    override def fmap[A, B](fa: Option[A])(f: A => B): Option[B] = fa  map f
  }
  val futureFunctorInstance: Functor[Future] = new Functor[Future] {
    override def fmap[A, B](fa: Future[A])(f: A => B): Future[B] = fa  map f
  }

  type EitherA[A] = Either[A, _] //type alias to circumvent type-inference error
    val leftBiasFunctorInstance: Functor[EitherA] = new Functor[EitherA] {
      override def fmap[A, C](either: EitherA[A])(f: A => C): EitherA[C] =  either match {
        case Left(a) => Left( f(a) )
        case Right(a) => Right(a)
      }
  }


  case class Money(currency: String, amount: Double)
  case class Wallet(id: Int, money: Money, password: String)
  case class AcmeWallet(id: String, money: Money, password: String)

  val idConverter: Int => String = ???
  val cryptographicHash: Array[Byte] => Array[Byte] = ???

  val asString: Array[Byte] => String = bytes => new String(bytes, "utf-8")
  val asByteArray: String => Array[Byte] = string => string.getBytes

  val encrypt: String =>  String = asString compose cryptographicHash compose asByteArray

  val encryptWalletPassword: Wallet => Wallet = wallet => 
    wallet.copy(password=encrypt(wallet.password))

  val asAcmeWallet: Wallet => AcmeWallet = wallet => 
    AcmeWallet(
      id=idConverter(wallet.id),
      money=wallet.money,
      password=wallet.password)

  val walletTransformer: Wallet =>  AcmeWallet =
      asAcmeWallet compose encryptWalletPassword  

  import Tree._

  implicit val treeFunctorInstance: Functor[Tree] = new Functor[Tree] {
    override def fmap[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree  match {
      case Leaf(a) => Leaf[B](f(a))
      case Node(leftBranch, rightBranch)  => Node(fmap(leftBranch)(f), fmap(rightBranch)(f))
    }
  }

  def treeTransformer(walletIncTree: Tree[Wallet])(implicit treeFunctor: Functor[Tree]): Tree[AcmeWallet] = 
    treeFunctor.fmap(walletIncTree)(walletTransformer)
}
