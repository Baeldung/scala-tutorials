package com.baeldung.scala.scalaz

import java.util.NoSuchElementException

import com.baeldung.scala.scalaz.ScalazExamples._
import org.junit.Test
import org.junit.Assert._

import scalaz.Scalaz._
import scalaz._

class ScalazExamplesUnitTest {

  @Test
  def givenTwoValues_whenComparedWithScalaz_thenReturnValidResult: Unit = {
    val val1 = 15
    val val2 = 25
    val val3 = 15

    assertTrue(val1 === val3)
    assertTrue(val1 =/= val2)
  }
  @Test
  def givenValuesOfDifferentTypes_whenComparedWithScala_thenItWorks: Unit = {
    val intValue = 10
    val strValue = "10"

    assertFalse(intValue == strValue)
  }

  @Test
  def givenScores_thenGreaterThanCheckWorks: Unit = {
    val score1 = Score(0.9)
    val score2 = Score(0.8)

    assertTrue(score1 > score2)
    assertTrue(score1 gt score2)
    assertTrue(score1 gte score2)
  }

  @Test
  def givenScores_thenLessThanCheckWorks: Unit = {
    val score1 = Score(0.9)
    val score2 = Score(0.8)

    assertFalse(score1 < score2)
    assertFalse(score1 lt score2)
    assertFalse(score1 lte score2)
  }

  @Test
  def givenScores_thenReturnsOrderingRelation: Unit = {
    val score1 = Score(0.9)
    val score2 = Score(0.8)

    assertEquals(Ordering.GT, score1 ?|? score2)
    assertEquals(Ordering.LT, score2 ?|? score1)
    assertEquals(Ordering.EQ, score1 ?|? score1)
  }

  @Test
  def givenValue_thenShowGivesStringRepresentation: Unit = {
    val str1 = 3.shows
    val str2 = 3.4.shows

    assertEquals("3", str1)
    assertEquals("3.4", str2)
  }

  @Test
  def givenCharRange_thenGenerateAsEnum: Unit = {
    val enum = 'a' |-> 'g'
    val enumAsList = enum.toList

    val expectedResult = IList('a', 'b', 'c', 'd', 'e', 'f', 'g')
    val expectedResultAsList = List('a', 'b', 'c', 'd', 'e', 'f', 'g')

    assertEquals(expectedResult, enum)
    assertEquals(expectedResultAsList, enumAsList)
  }

  @Test
  def givenEnumValue_thenGetSuccessorAndPredecessor: Unit = {
    val successor = 'b'.succ
    val predecessor = 'b'.pred

    val expectedSuccessor = 'c'
    val expectedPredecessor = 'a'

    assertEquals(expectedSuccessor, successor)
    assertEquals(expectedPredecessor, predecessor)
  }

  @Test
  def givenEnumValue_thenCanGetValueNStepsAhead: Unit = {
    val valueAhead = 'b' -+- 3
    val valueAhead2 = 'b' -+- 4

    val expected1 = 'e'
    val expected2 = 'f'

    assertEquals(expected1, valueAhead)
    assertEquals(expected2, valueAhead2)
  }

  @Test
  def givenEnumValue_thenCanGetValueNStepsBehind: Unit = {
    val valueBehind = 'e' --- 3
    val valueBehind2 = 'c' --- 2

    val expected1 = 'b'
    val expected2 = 'a'

    assertEquals(expected1, valueBehind)
    assertEquals(expected2, valueBehind2)
  }

  @Test
  def givenEnum_thenCanTryToGenerateMinAndMaxValues: Unit = {
    val intMin = Enum[Int].min
    val intMax = Enum[Int].max

    val expectedMin = Some(-2147483648)
    val expectedMax = Some(2147483647)

    assertEquals(expectedMin, intMin)
    assertEquals(expectedMax, intMax)
  }

  @Test
  def givenCustomEnum_thenExhibitAllImplementedBehaviour: Unit = {
    // generate range
    val expectedEnum =
      IList(Priority(1, "LOW"), Priority(2, "MEDIUM"), Priority(3, "HIGH"))
    assertEquals(expectedEnum, LOW |-> HIGH)
    // range to list
    assertEquals(expectedEnum.toList, (LOW |-> HIGH).toList)

    // pred and succ
    assertEquals(HIGH, LOW.pred)
    assertEquals(HIGH, MEDIUM.succ)

    // step forward and back
    assertEquals(MEDIUM, HIGH -+- 2)
    assertEquals(LOW, LOW --- 3)

    // min and max
    assertEquals(Some(Priority(3, "HIGH")), Enum[Priority].max)
    assertEquals(Some(Priority(1, "LOW")), Enum[Priority].min)
  }

  @Test
  def givenAnyValue_thenCanCreateOptionOfGivenType: Unit = {
    val intOpt = some(12)
    val expected = Some(12)

    val intOpt2 = none[Int]
    val expected2 = None

    assertEquals(expected, intOpt)
    assertEquals(expected2, intOpt2)
  }

  @Test
  def givenAnyValue_thenCanConvertToOption: Unit = {
    val intOpt = 13.some
    val strOpt = "baeldung".some

    assertEquals(Some(13), intOpt)
    assertEquals(Some("baeldung"), strOpt)
  }

  @Test
  def givenOption_thenCanExtractValuesWithSomeNoneOperator: Unit = {
    val opt = some(12)
    val value1 = opt some { a =>
      a
    } none 0

    val value2 = opt
      .some(_ * 2)
      .none(0)

    assertEquals(12, value1)
    assertEquals(24, value2)
  }

  @Test
  def givenOption_thenCanExtractValueWithPipeOperator: Unit = {
    val opt = some(12)
    val opt2 = none[Int]

    val value1 = opt | 0
    val value2 = opt2 | 5

    assertEquals(12, value1)
    assertEquals(5, value2)
  }

  @Test
  def givenOption_thenCanExtractValueWithUnaryOperator: Unit = {
    val someInt = some(25)
    val emptyInt = none[Int]
    val someStr = some("baeldung")
    val emptyStr = none[String]

    assertEquals(25, ~someInt)
    assertEquals(0, ~emptyInt)
    assertEquals("baeldung", ~someStr)
    assertEquals("", ~emptyStr)
  }

  @Test
  def givenString_whenPluralApplied_thenReturnPluralForm: Unit = {
    assertEquals("apples", "apple".plural(2))
    assertEquals("tries", "try".plural(2))
    assertEquals("range rovers", "range rover".plural(2))
  }

  @Test
  def givenTwoValues_whenFoldedOverWithBoolean_thenReturnsRightValue: Unit = {
    val t = true
    val f = false

    val expectedValueOnTrue = "it was true"
    val expectedValueOnFalse = "it was false"

    val actualValueOnTrue =
      t.fold[String](expectedValueOnTrue, expectedValueOnFalse)
    val actualValueOnFalse =
      f.fold[String](expectedValueOnTrue, expectedValueOnFalse)

    assertEquals(expectedValueOnTrue, actualValueOnTrue)
    assertEquals(expectedValueOnFalse, actualValueOnFalse)
  }

  @Test
  def givenValue_thenOptionallyReturnBasedOnBoolean: Unit = {
    val t = true
    val f = false

    val restrictedData = "Some restricted data"

    val expectedValueOnTrue = Some(restrictedData)
    val expectedValueOnFalse = None

    val actualValueOnTrue = t option restrictedData
    val actualValueOnFalse = f option restrictedData

    assertEquals(expectedValueOnTrue, actualValueOnTrue)
    assertEquals(expectedValueOnFalse, actualValueOnFalse)
  }

  @Test
  def givenTwoValues_whenTernaryOpApplied_thenReturnCorrectOne: Unit = {
    val t = true
    val f = false

    assertEquals("true", t ? "true" | "false")
    assertEquals("false", f ? "true" | "false")
  }

  @Test
  def givenValue_thenReturnIfBooleanTrueElseZeroValue: Unit = {
    val t = true
    val f = false

    assertEquals("string value", t ?? "string value")
    assertEquals("", f ?? "string value")

    assertEquals(List(1, 2, 3), t ?? List(1, 2, 3))
    assertEquals(List(), f ?? List(1, 2, 3))

    assertEquals(5, t ?? 5)
    assertEquals(0, f ?? 5)
  }

  @Test
  def givenValue_thenReturnIfBooleanFalseElseZeroValue: Unit = {
    val t = true
    val f = false

    assertEquals("string value", f !? "string value")
    assertEquals("", t !? "string value")

    assertEquals(List(1, 2, 3), f !? List(1, 2, 3))
    assertEquals(List(), t !? List(1, 2, 3))

    assertEquals(5, f !? 5)
    assertEquals(0, t !? 5)
  }

  @Test
  def givenMap_whenValueAlteredWithMapOps_thenReturnNewMap(): Unit = {
    val map = Map("a" -> 1, "b" -> 2)
    val expectedResult1 = Map("a" -> 1, "b" -> 20)
    val expectedResult2 = Map("a" -> 1, "b" -> 2, "c" -> 3)

    val mapAfterAlter1 = map.alter("b") { maybeValue =>
      maybeValue
        .some(v => some(v * 10))
        .none(some(0))
    }
    val mapAfterAlter2 = map.alter("c") { maybeValue =>
      maybeValue
        .some(v => some(v * 10))
        .none(some(3))
    }

    assertEquals(expectedResult1, mapAfterAlter1)
    assertEquals(expectedResult2, mapAfterAlter2)

  }

  @Test
  def givenTwoMaps_whenIntersected_thenReturnIntersectionMap(): Unit = {
    val m1 = Map("a" -> 1, "b" -> 2)
    val m2 = Map("b" -> 2, "c" -> 3)
    val m3 = Map("a" -> 5, "b" -> 8)

    val intersectionMap1 = m1.intersectWith(m2)(_ + _)
    val intersectionMap2 = m1.intersectWith(m2)((v1, v2) => v1 * v2)
    val intersectionMap3 = m1.intersectWith(m3)(_ - _)

    val expectedMap1 = Map("b" -> 4)
    val expectedMap2 = Map("b" -> 4)
    val expectedMap3 = Map("a" -> -4, "b" -> -6)

    assertEquals(expectedMap1, intersectionMap1)
    assertEquals(expectedMap2, intersectionMap2)
    assertEquals(expectedMap3, intersectionMap3)
  }

  @Test
  def givenMap_whenMapKeysOpApplied_thenUpdateKeys(): Unit = {
    val m1 = Map("a" -> 1, "b" -> 2)

    val mapWithUpdatedKeys = m1.mapKeys(_.toUpperCase)
    val expectedMap = Map("A" -> 1, "B" -> 2)

    assertEquals(expectedMap, mapWithUpdatedKeys)
  }

  @Test
  def givenTwoMaps_whenUnionApplied_thenReturnTheirUnion(): Unit = {
    val m1 = Map("a" -> 1, "b" -> 2)
    val m2 = Map("b" -> 2, "c" -> 3)

    val unionMap = m1.unionWith(m2)(_ + _)
    val expectedMap = Map("a" -> 1, "b" -> 4, "c" -> 3)

    assertEquals(expectedMap, unionMap)
  }
  @Test
  def givenMap_whenInsertingDuplicateKey_thenMergeValuesWithFunction: Unit = {
    val m1 = Map("a" -> 1, "b" -> 2)

    val insertResult1 = m1.insertWith("a", 99)(_ + _)
    val insertResult2 = m1.insertWith("c", 99)(_ + _)

    val expectedResult1 = Map("a" -> 100, "b" -> 2)
    val expectedResult2 = Map("a" -> 1, "b" -> 2, "c" -> 99)

    assertEquals(expectedResult1, insertResult1)
    assertEquals(expectedResult2, insertResult2)
  }

  @Test
  def givenList_whenFetchingHead_thenReturnFirstElement: Unit = {
    val list = List(1)

    assertEquals(1, list.head)
  }

  @Test(expected = classOf[NoSuchElementException])
  def givenEmptyList_whenFetchingHead_thenThrowsException(): Unit = {
    val list = List()

    list.head
  }

  @Test
  def givenValues_thenCreateNonEmptyList: Unit = {
    // wrap a value in a nel
    val nel1 = 1.wrapNel
    assertEquals(NonEmptyList(1), nel1)

    // standard apply
    val nel2 = NonEmptyList(3, 4)

    // cons approach
    val nel3 = 2 <:: nel2

    assertEquals(NonEmptyList(2, 3, 4), nel3)

    // append
    val nel4 = nel1 append nel3
    assertEquals(NonEmptyList(1, 2, 3, 4), nel4)
  }

  @Test
  def givenUserObject_thenUpdateFullNameWithScalaWorks(): Unit = {
    val name = FullName(fname = "John", lname = "Doe")
    val userId = UserId(10)
    val user = User(userId, name)

    val updatedName = FullName("Marcus", "Aurelius")
    val actual = user.copy(name = updatedName)

    assertEquals(User(userId, updatedName), actual)
  }

  @Test
  def givenUserObject_thenUpdateFirstNameWithScalaWorks(): Unit = {
    val name = FullName(fname = "John", lname = "Doe")
    val userId = UserId(10)
    val user = User(userId, name)

    val updatedName = FullName("Jane", "Doe")
    val actual = user.copy(name = name.copy(fname = "Jane"))

    assertEquals(User(userId, updatedName), actual)
  }

  @Test
  def givenUserObject_thenUpdateFullNameWithLensWorks(): Unit = {
    val name = FullName(fname = "John", lname = "Doe")
    val userId = UserId(10)
    val user = User(userId, name)

    val updatedName = FullName("Marcus", "Aurelius")
    val actual = userFullName.set(user, updatedName)

    assertEquals(User(userId, updatedName), actual)
  }

  @Test
  def givenUserObject_thenUpdateFirstNameWithLensWorks(): Unit = {
    val name = FullName(fname = "John", lname = "Doe")
    val userId = UserId(10)
    val user = User(userId, name)

    val updatedName = FullName("Jane", "Doe")
    val actual = userFirstName.set(user, "Jane")

    assertEquals(User(userId, updatedName), actual)
  }
}
