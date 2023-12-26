package com.baeldung.scala.strings.passwordgen

import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class RandomPasswordGeneratorUnitTest
  extends AnyWordSpec
  with Matchers
  with ScalaCheckPropertyChecks {

  "RandomPasswordGenerator" should {
    "generate alpha numeric random string" in {
      forAll(Gen.const(())) { _ =>
        val pwd = RandomPasswordGenerator.randomAlphaNumericString
        pwd should have size 16
        pwd.forall(_.isLetterOrDigit) shouldBe true
      }
    }

    "generate random string - might contain alpha-numeric-special" in {
      forAll(Gen.const(())) { _ =>
        val pwd = RandomPasswordGenerator.randomString
        pwd should have size 16
        pwd.exists(_ == ' ') shouldBe false
      }
    }

    "generate random string - might contain alpha-numeric and only special characters *, _, &, @" in {
      forAll(Gen.const(())) { _ =>
        val pwd = RandomPasswordGenerator.randomStringWithSpecificSpecialChars
        withClue(s"Failed for generated password : $pwd ") {
          pwd should have size 16
          pwd.exists(_ == ' ') shouldBe false
          pwd.exists(Set('!', ')', '(', '^').contains(_)) shouldBe false
        }
      }
    }

    "generate alpha numeric secure password" in {
      forAll { (_: Int) =>
        val password = RandomPasswordGenerator.randomAlphaNumericSecurePwd
        password.length shouldBe 16
        password.forall(_.isLetterOrDigit) shouldBe true
      }
    }

    "generate secure password including special characters" in {
      forAll { (_: Unit) =>
        val password = RandomPasswordGenerator.randomSecurePwdWithSpecialChars
        password.length shouldBe 16
        password.forall(c => (33 to 126).contains(c.toInt)) shouldBe true
      }
    }

    "generate secure password excluding some special characters" in {
      forAll { (_: Unit) =>
        val password = RandomPasswordGenerator.randomSecurePwdWithExclusions
        password.length shouldBe 16
        password should not contain oneOf('O', 'o', 'l', '1', '0', '`')
      }
    }

    "generate secure password with mix of condition" in {
      forAll { (_:Unit) =>
        val password = RandomPasswordGenerator.randomSecurePwdWithMix
        password.length shouldBe 16
        password.exists(_.isLower) shouldBe true
        password.exists(_.isUpper) shouldBe true
        password.exists(_.isDigit) shouldBe true
        password.exists(IndexedSeq('!', '@', '#', '$', '&', '*', '?', '^', '(', ')').contains) shouldBe true
      }
    }
  }

}
