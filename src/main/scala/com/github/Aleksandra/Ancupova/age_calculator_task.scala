package com.github.Aleksandra.Ancupova

import java.time.Year
import scala.io.StdIn.readLine

object age_calculator_task extends App {
  val myName = readLine("What is your name?")
  println(s"Hi $myName!")
  val myAge = readLine("How old are you?").toInt
  val year = Year.now.getValue
  val targetAge = 100
  val whenHundred = year - myAge + targetAge
  println(s"Cool, you'll be 100 in $whenHundred")


}
