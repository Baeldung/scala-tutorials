

case class UserData(username: String, password: String)

sealed trait DomainValidation {
  def error: String
}

case object UsernameHasSpaces extends DomainValidation {
  def error: String = "Username can not contain spaces"
}

case object PasswordTooShort extends DomainValidation {
  def error: String = "Password is too short."
}


import cats.data.ValidatedNec
import cats.implicits._

object ValidationFactory {

  type ValidationResult[A] = ValidatedNec[DomainValidation, A]

  private def validateUserName(userName: String): ValidationResult[String] =
    if (userName.matches("^[a-zA-Z0-9]*$")) userName.validNec else UsernameHasSpaces.invalidNec

  private def validatePassword(password: String): ValidationResult[String] =
    if (password.length > 8) password.validNec
    else PasswordTooShort.invalidNec

  def validateUserData(username: String, password: String): ValidationResult[UserData] = {
    (validateUserName(username), validatePassword(password))
      .mapN(UserData)
  }
}

ValidationFactory.validateUserData(
  "validUsername",
  "validPassword"
)
// ValidationFactory.ValidationResult[UserData] = Valid(UserData(validUsername,validPassword))

ValidationFactory.validateUserData(
  "oops s",
  "invalid"
)
// ValidationFactory.ValidationResult[UserData] = Invalid(Chain(UsernameHasSpaces, PasswordTooShort))