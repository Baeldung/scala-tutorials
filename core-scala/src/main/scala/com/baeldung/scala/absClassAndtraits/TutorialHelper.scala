package com.baeldung.scala.absClassAndtraits



object TutorialHelper {

  def countryFinder(place: String): String =
    if (place == "Dublin") "Ireland"
    else if (place == "Bucharest") "Romania"
    else if (place == "New York")  "USA"
    else if (place == "Calcutta")  "India"
    else "Unknown"

  abstract class Person (val name:String, val age: Int, placeOfBirth: String) {
    def isASeniorCitizen: Boolean = age >= 60
    def belongsToCountry: String
  }

  class Employee(empCode: Int, name: String, age: Int, placeOfBirth: String,  placeOfWork: String, workingForYears: Int)
    extends Person(name,age, placeOfBirth) {
    def isEligibleForSpecialAllowance: Boolean = super.isASeniorCitizen && this.workingForYears > 10
    def isWorkingAtHQ: Boolean = this.placeOfWork == "HQ"
    override def belongsToCountry: String = countryFinder(this.placeOfBirth)
  }

  trait CanFly {
    val minWings: Int
    var extraWings: Int = 0
    def flyFor(howLong: Int): Int = (minWings + extraWings) * 2 + howLong
  }

  trait CanSing {
    def requestASong (firstLine: String): String
  }

  trait CanPlaySoccer {
    def showDribblingSkills(notEarlierThanYears: Int): String // Link to a youtube video
  }

  class BirdLikeEmployee(
    empCode: Int, name: String, age: Int, placeOfBirth: String,  placeOfWork: String, workingForYears: Int)
  extends CanFly {
    override val minWings: Int = 2
  }

  class SuperWomanLikeEmployee(
      empCode: Int, name: String, age: Int, placeOfBirth: String,  placeOfWork: String, workingForYears: Int)
    extends CanFly {
      override val minWings: Int = 2
      extraWings = 2
      override def flyFor(howLong: Int): Int = (minWings * extraWings) * howLong * 10
  }

  class SingerAndFlierEmployee(
    empCode: Int, name: String, age: Int, placeOfBirth: String,  placeOfWork: String, workingForYears: Int)
    extends Employee(empCode,name,age, placeOfBirth,placeOfWork, workingForYears) with CanFly with CanSing {

      override def belongsToCountry: String = countryFinder(this.placeOfBirth)
      override val minWings: Int = 4
      extraWings = 2
      override def requestASong(firstLine: String): String = "www.youtube.com/bundleInOfficeLibrary.mp4"
      override def flyFor(howLong: Int): Int = (minWings * extraWings) * howLong * 10
  }


}
