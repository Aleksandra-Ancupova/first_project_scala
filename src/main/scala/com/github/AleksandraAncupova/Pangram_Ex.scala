package com.github.AleksandraAncupova

object Pangram_Ex extends App {
  //TODO write a pangram tester
  //https://en.wikipedia.org/wiki/Pangram
  //check if text contains every letter of an alphabet at least once
  //for this task consider upper and lower case the same
  // def isPangram(text:String, alpha:String="abcdefghijklmnopqrstuvwxyz"):Boolean = {
  // false //FIXME }
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toSet
  println(alphabet)


  val myTestText = "The five boxing wizards jump quickly."
  // println(isPangram(myTestText)) //should be true
}
