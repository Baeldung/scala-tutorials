package com.baeldung.scala.strings.passwordgen

import java.security.SecureRandom
import scala.util.Random

object RandomPasswordGenerator {

  def randomAlphaNumericString: String = {
    Random.alphanumeric.take(16).mkString
  }

  def randomString: String = {
    Iterator.continually(Random.nextPrintableChar()).take(16).mkString
  }

  def randomStringWithSpecificSpecialChars: String = {
    def isSupportedChar(char: Char): Boolean =
      char.isLetterOrDigit || Set('*', '_', '&', '@').contains(char)
    Iterator
      .continually(Random.nextPrintableChar())
      .filter(isSupportedChar)
      .take(16)
      .mkString
  }

  def randomAlphaNumericSecurePwd: String = {
    val alphanumericChars = ('0' to '9') ++ ('A' to 'Z') ++ ('a' to 'z')
    val random = new SecureRandom()
    Iterator
      .continually(alphanumericChars(random.nextInt(alphanumericChars.length)))
      .take(16)
      .mkString
  }

  def randomSecurePwdWithSpecialChars: String = {
    val printableChars = (33 to 126).map(_.toChar) // ASCII printable characters
    val random = new SecureRandom()
    Iterator
      .continually(printableChars(random.nextInt(printableChars.length)))
      .take(16)
      .mkString
  }

  def randomSecurePwdWithExclusions: String = {
    val printableChars = (33 to 126).map(_.toChar) // ASCII printable characters
    val excludedChars = Set('O', 'o', 'l', '1', '0', '`')
    val random = new SecureRandom()
    Iterator
      .continually(printableChars(random.nextInt(printableChars.length)))
      .filterNot(excludedChars.contains)
      .take(16)
      .mkString
  }

  def randomSecurePwdWithMix: String = {
    val lowerLetters = 'a' to 'z'
    val upperLetters = 'A' to 'Z'
    val numbers = '0' to '9'
    val specialChars =
      IndexedSeq('!', '@', '#', '$', '&', '*', '?', '^', '(', ')')
    val fullCharset = lowerLetters ++ upperLetters ++ numbers ++ specialChars
    val random = new SecureRandom()

    def pickRandomChars(charSet: IndexedSeq[Char], size: Int): List[Char] = {
      Iterator
        .continually(charSet(random.nextInt(charSet.length)))
        .take(size)
        .toList
    }

    val passwordChars: List[Char] = pickRandomChars(lowerLetters, 1) ++
      pickRandomChars(upperLetters, 1) ++
      pickRandomChars(numbers, 1) ++
      pickRandomChars(specialChars, 1) ++
      pickRandomChars(fullCharset, 12)

    Random.shuffle(passwordChars).mkString

  }

}
