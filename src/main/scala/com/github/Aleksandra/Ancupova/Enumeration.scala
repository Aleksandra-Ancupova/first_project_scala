package com.github.Aleksandra.Ancupova

import com.github.Aleksandra.Ancupova.Weekdays.Day

import scala.Console.println

object Weekdays extends Enumeration {

  type Day = Value

  val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value

}

object Enumeration extends App {
  println(Weekdays.Monday)

  def getDayType(someDay: Day): Unit = {
    if ((someDay == Weekdays.Saturday) || (someDay == Weekdays.Sunday)) println("That's weekend")
    else println("That's a work day")
  }

  getDayType(Weekdays.Sunday)
  getDayType(Weekdays.Monday)

}
