package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object bonus_exercise_day4 extends App {

  println("Xmas bonus calculator")
  //TODO
  //Ask for person's name
  //Ask for how long they have worked at the firm
  //Ask for monthly wage
  //Calculate Xmas bonus if they have worked at least 2  years otherwise print sorry no bonus
  //Bonus is years worked over 2 years * 15% of monthly wage
  //so 5 years worked, 1000 Euros wage would give 450 Euro bonus (3 years * 150)

  val name = readLine("Hi, what's your name?")
  val tenure = readLine(s"How long have you worked at CocaCola, $name?").toInt
  val salary = readLine("And what is your monthly salary now?").toInt
  val bonusPercent = 0.15

  if (tenure > 2) {
    val bonus = ((tenure - 2) * salary * bonusPercent).toInt
    println(s"Congrats! Your Christmas bonus is $bonus")
  }
  else println("Sorry, your tenure is too low for a bonus")

}
