package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object temperature_exercise extends App {

  val name = readLine(s"What's your name?")
  val temp = readLine(s"What's your temperature, $name?").toDouble
  if (temp < 35) {
    println("That is a bit too cold")
  }
  else if (temp > 37) {
    println("You have a fever! Please consider contacting a doctor")
  }
  else println("You are all right!")

}
