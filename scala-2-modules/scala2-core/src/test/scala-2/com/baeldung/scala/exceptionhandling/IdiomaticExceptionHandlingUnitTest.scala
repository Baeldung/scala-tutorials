package com.baeldung.scala.exceptionhandling

import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import com.baeldung.scala.exceptionhandling.LegacyErrors._
import com.baeldung.scala.exceptionhandling.ValidationErrors._
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

import java.io.IOException
import scala.util.{Failure, Success, Try}

class IdiomaticExceptionHandlingUnitTest
  extends AnyFeatureSpec
  with GivenWhenThen
  with Matchers {
  val user = User("user1", "password1")
  val rootUser = User("root", "root_password")

  Feature("Legacy code invocation") {
    Scenario("Accidentally Using Null value as an object") {
      Given("Unknown user as a request gives a null as response")
      val nullSession: Session = LegacyService.authenticate(null)

      When("We try to access an objects field")
      Then("NullPointerException should be thrown")
      intercept[NullPointerException] {
        val nullSession = LegacyService.authenticate(null)
        val id: String = nullSession.id
      }
    }

    Scenario("Legacy code could throw an exception") {
      Given("Outage on connectivity level")
      When("We try to invoke a legacy code")
      Then("IOException could be thrown")
      intercept[IOException] {
        val exceptionResource =
          LegacyService.getResource("/", Session("sessionId", 3600))
      }
    }
  }

  Feature("Use Option[T] to guard against null") {
    Scenario("Box legacy code in Option[T] while calling") {
      Given("Optional result")
      val optionalSession = Option(LegacyService.authenticate(null))

      When("We check a result")
      val isDefined = optionalSession.isDefined

      Then("It's undefined")
      isDefined should be(false)
      optionalSession should be(None)
    }

    Scenario("Provide an adapted version of a legacy code with Option[T]") {
      Given("Call to an adapter")
      val optionalSession = HandlingWithOption.getResourceOptional(null, null)

      When("We check a result")
      val isDefined = optionalSession.isDefined

      Then("It's undefined")
      isDefined should be(false)
      optionalSession should be(None)
    }

    Scenario("Use Optional value in an idiomatic way. Check None reply") {
      Given("Optional result of adapted invocation")
      val noneSession = HandlingWithOption.authenticateOptional(null)

      When("a function applied to the result boxed in Optional")
      val noneId = noneSession.map(_.id.toUpperCase)

      Then("Fast-fail Scenario works for None value. No access to null object")
      noneId should be(None)
    }

    Scenario("Use Optional value in an idiomatic way. Check Some reply") {
      Given("Optional result of adapted invocation")
      val someSession = HandlingWithOption.authenticateOptional(user)

      When("a function applied to the result boxed in Optional")
      val someId = someSession.map(_.id.toUpperCase)

      Then("Function applied to existed value boxed in Option")
      someId should be(Some("USER1_SESSION"))
    }

    Scenario("Option could be used in a for-comprehension") {
      Given("Option has map/flatMap functionality")
      When("Used in a for-comprehension")
      val someUppercaseId = for {
        user <- HandlingWithOption.authenticateOptional(user)
      } yield {
        user.id.toUpperCase
      }
      Then("Modified value, boxed in an Option is returned")
      someUppercaseId should be(Some("USER1_SESSION"))
    }

    Scenario(
      "Multiple Option values could be processed in the same for-comprehension"
    ) {
      Given("Composed for-comprehension")
      val resourceOptional = for {
        session <- HandlingWithOption.authenticateOptional(user)
        resource <- HandlingWithOption.getResourceOptional("/smile", session)
      } yield {
        resource.value
      }

      Given("Composed map/flatMap chained call")
      val resourceOptionalFromMap = HandlingWithOption
        .authenticateOptional(user)
        .flatMap(
          HandlingWithOption.getResourceOptional("/smile", _).map(_.value)
        )

      When("Result values are compared")
      Then("For-comprehension is a 'sugar' for map/flatMap composition")
      resourceOptional should be(resourceOptionalFromMap)
      resourceOptional should be(Some("(:"))
    }

    Scenario(
      "For-comprehension should fail-fast whenever it reaches a None value in a chain of computations"
    ) {
      Given("Composed for-comprehension with failed call to authenticate")
      val resourceOptional = for {
        session <- HandlingWithOption.authenticateOptional(null)
        resource <- HandlingWithOption.getResourceOptional("/smile", session)
      } yield {
        resource.resourceId
      }

      When("Result is checked")
      Then(
        "It failed for the first call, with result None, and consequent calls will never happen"
      )
      resourceOptional should be(None)
    }
  }

  Feature("Use Try[T] to guard against null") {
    Scenario("Box legacy code in Try[T] while calling") {
      Given("Try result")
      val failedSession = Try(LegacyService.authenticate(rootUser))
      val successSession = Try(LegacyService.authenticate(user))

      When("We check a result")
      val isFailure = failedSession.isFailure
      val isSuccess = successSession.isSuccess

      Then("Result is failed or succeed")
      isFailure should be(true)
      failedSession shouldBe a[Failure[_]]
      failedSession.failed.get shouldBe a[IllegalArgumentException]

      isSuccess should be(true)
      successSession should be(Success(Session("user1_session", 3600)))
    }

    Scenario("Try result could be pattern matched") {
      Given("Failed response boxed in Try")
      val failedSession = Try(LegacyService.authenticate(rootUser))

      When("Matching")
      val result = failedSession match {
        case Failure(ex)      => "Failure"
        case Success(session) => "Success"
      }

      Then("Matched result is Failure")
      result should be("Failure")
    }

    Scenario("Try could be used in a for-comprehension") {
      Given("Try has map/flatMap functionality")
      When("Used in a for-comprehension")
      val someUppercaseId = for {
        user <- HandlingWithTry.tryAuthenticate(user)
      } yield {
        user.id.toUpperCase
      }

      Then("Modified value, boxed in an Try is returned")
      someUppercaseId should be(Success("USER1_SESSION"))
    }

    Scenario("Try can't guard against null") {
      Given("Legacy code boxed in a Try[T]")
      When("Response is null")
      val nullSession = HandlingWithTry.tryAuthenticate(null)

      Then("Access to an object field will throw a NulPointerException")
      intercept[NullPointerException] {
        val sessionId = nullSession.get.id
      }
    }
  }

  Feature("Combining Try and Option against legacy code") {
    Scenario("Combining Try[T] and Option[T] will guard against exceptions") {
      Given(
        "Legacy code boxed in the Try/Option and combined with for-comprehension"
      )
      When("Legacy code throws exception")
      val tryOptionFailedResource =
        HandlingWithTryOption.getTryOptionResourceValue(user, "/")

      Then("Exception is boxed in a Failure")
      tryOptionFailedResource shouldBe a[Failure[_]]
      tryOptionFailedResource.failed.get shouldBe a[IOException]
    }

    Scenario("Combining Try[T] and Option[T] will guard against null") {
      Given(
        "Legacy code boxed in the Try/Option and combined with for-comprehension"
      )
      When("Legacy code throws exception")
      val tryOptionFailedResource =
        HandlingWithTryOption.getTryOptionResourceValue(null, "/")

      Then("Exception is boxed in a Failure")
      tryOptionFailedResource shouldBe a[Failure[_]]
      tryOptionFailedResource.failed.get shouldBe a[NoSuchElementException]
    }

    Scenario("Combining Try[T] ans Option[T]") {
      Given(
        "Legacy code boxed in the Try/Option and combined with for-comprehension"
      )
      When("Legacy code reply with success")
      val tryOptionSuccessResource =
        HandlingWithTryOption.getTryOptionResourceValue(user, "/smile")

      Then("Result is boxed in a Success")
      tryOptionSuccessResource should be(Success(Some("(:")))
    }
  }

  Feature("Either allows us to use a custom type as an Error") {
    Scenario("User not found") {
      Given("Legacy code handled by Either and Try")
      When("Legacy code returns null when user not found")
      val eitherLeftResource =
        HandlingWithEither.eitherGetResourceValue(null, "/")

      Then("We translate null into Left with custom error type")
      eitherLeftResource should be(Left(UserNotFound(null)))
    }

    Scenario("Server Error") {
      Given("Legacy code handled by Either and Try")
      When(
        "Legacy code throws exception when user not satisfy an inner criteria"
      )
      val eitherLeftResource =
        HandlingWithEither.eitherGetResourceValue(rootUser, "/")

      Then("We translate exception into Left with custom error type")
      eitherLeftResource shouldBe a[Left[_, _]]
      eitherLeftResource.left.get shouldBe a[ServerError]
    }

    Scenario("Resource not Found") {
      Given("Legacy code handled by Either and Try")
      When("Legacy code throws exception when requested resource not found")
      val eitherLeftResource =
        HandlingWithEither.eitherGetResourceValue(user, null)

      Then("We translate exception into Left with custom error type")
      eitherLeftResource shouldBe a[Left[_, _]]
      eitherLeftResource.left.get shouldBe a[ResourceNotFound]
    }
  }

  Feature(
    "MonadError is an abstraction over error handling tools. Option could be used as a concrete error container."
  ) {
    Given("Imported instance of a Type Class of MonadError for Option")
    import cats.instances.option._
    Given("Error Adapter to convert errors into Option compatible format")
    implicit val errorAdoption: LegacyErrors => Unit = error => ()

    Scenario("User not found exception handled by Option") {
      When("We call legacy functionality backed by MonadError")
      val optionMonadErrorResult =
        HandlingWithMonadError.getResourceValue(null, "/smile")

      Then("Thrown exception converted into None for Option")
      optionMonadErrorResult should be(None)
    }
    Scenario("Server Error exception handled by Option") {
      When("We call legacy functionality backed by MonadError")
      val optionMonadErrorResult =
        HandlingWithMonadError.getResourceValue(rootUser, "/smile")

      Then("Thrown exception converted into None for Option")
      optionMonadErrorResult should be(None)
    }
    Scenario("Resource not found exception handled by Option") {
      When("We call legacy functionality backed by MonadError")
      val optionMonadErrorResult =
        HandlingWithMonadError.getResourceValue(user, "/")

      Then("Thrown exception converted into None for Option")
      optionMonadErrorResult should be(None)
    }
    Scenario("Successful call handled by Option") {
      When("We call legacy functionality backed by MonadError")
      val optionMonadErrorResult =
        HandlingWithMonadError.getResourceValue(user, "/smile")

      Then("We got result boxed in Some")
      optionMonadErrorResult should be(Some("(:"))
    }
  }

  Feature(
    "MonadError is an abstraction over error handling tools. Either could be used as a concrete error container."
  ) {
    Given("Imported instance of a Type Class of MonadError for Either")
    import cats.instances.either._
    Given("Type adaptor to make Either accept one type parameter")
    type EitherAdaptor[T] = Either[LegacyErrors, T]
    Given("Either supports custom error type, so implicit adapter don't needed")

    Scenario("User not found exception boxed in Either") {
      When("We call legacy functionality backed by MonadError")
      val eitherMonadErrorResult = HandlingWithMonadError
        .getResourceValue[EitherAdaptor, LegacyErrors](null, null)

      Then("Thrown exception converted into Left with custom error for Either")
      eitherMonadErrorResult should be(Left(UserNotFound(null)))
    }

    Scenario("Resource not found exception boxed in Either") {
      When("We call legacy functionality backed by MonadError")
      val eitherMonadErrorResult = HandlingWithMonadError
        .getResourceValue[EitherAdaptor, LegacyErrors](user, null)

      Then("Thrown exception converted into Left with custom error for Either")
      eitherMonadErrorResult should be(Left(ResourceNotFound(null)))
    }

    Scenario(
      "Server error exception for IllegalArgumentException boxed in Either"
    ) {
      When("We call legacy functionality backed by MonadError")
      val eitherMonadErrorResult = HandlingWithMonadError
        .getResourceValue[EitherAdaptor, LegacyErrors](rootUser, "/")

      Then(
        "Thrown exception converted into Left with custom error for Either with maximum info about error"
      )
      eitherMonadErrorResult shouldBe a[Left[_, _]]
      eitherMonadErrorResult.left.get shouldBe a[ServerError]
      val serverErrorException =
        eitherMonadErrorResult.left.get.asInstanceOf[ServerError]
      serverErrorException.ex shouldBe a[IllegalArgumentException]
    }

    Scenario("Server error exception for IOException boxed in Either") {
      When("We call legacy functionality backed by MonadError")
      val eitherMonadErrorResult = HandlingWithMonadError
        .getResourceValue[EitherAdaptor, LegacyErrors](user, "/")

      Then(
        "Thrown exception converted into Left with custom error for Either with maximum info about error"
      )
      eitherMonadErrorResult shouldBe a[Left[_, _]]
      eitherMonadErrorResult.left.get shouldBe a[ServerError]
      val serverErrorException =
        eitherMonadErrorResult.left.get.asInstanceOf[ServerError]
      serverErrorException.ex shouldBe a[IOException]
    }

    Scenario("Successful result boxed in Either") {
      When("We call legacy functionality backed by MonadError")
      val eitherMonadErrorResult = HandlingWithMonadError
        .getResourceValue[EitherAdaptor, LegacyErrors](user, "/smile")

      Then("Result is boxed in Right")
      eitherMonadErrorResult should be(Right("(:"))
    }
  }

  Feature("Validated provides parallel error handling") {
    Scenario("Validating User with failed fields") {
      Given("User with invalid login and password")
      val invalidUser = User("invalidLogin_", null)

      When("User validation is called")
      val validationResult = HandlingWithValidated.validateUser(invalidUser)

      Then("Result should contains non empty list with 2 values")
      validationResult.isInvalid should be(true)
      validationResult should be(
        Invalid(
          NonEmptyList(
            IllegalLogin("invalidLogin_"),
            List(IllegalPassword(null))
          )
        )
      )
    }

    Scenario("Validating User with success fields") {
      Given("User with valid login and password")
      val validUser = User("validUser", "validPassword1_")

      When("User validation is called")
      val validationResult = HandlingWithValidated.validateUser(validUser)

      Then("Result should contains a valid User object")
      validationResult.isValid should be(true)
      validationResult should be(Valid(User("validUser", "validPassword1_")))
    }
  }
}
