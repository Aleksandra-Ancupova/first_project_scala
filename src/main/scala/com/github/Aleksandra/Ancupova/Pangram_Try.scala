package com.github.Aleksandra.Ancupova

object Pangram_Try extends App {
  //TODO write a pangram tester
  //https://en.wikipedia.org/wiki/Pangram
  //check if text contains every letter of an alphabet at least once
  //for this task consider upper and lower case the same

  def isPangram(text: String, alpha: String = "abcdefghijklmnopqrstuvwxyz"): Boolean = {
    val charSet = text.toLowerCase.toSet
    alpha.toSet.subsetOf(charSet)
  }

  false //FIXME }
  val myTestText = "The five boxing wizards jump quickly."
  println(isPangram(myTestText)) //should be true
  val charSet = myTestText.toSet
  println(charSet)

}
