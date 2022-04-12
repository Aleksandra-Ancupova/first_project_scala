package com.github.AleksandraAncupova

object functions_ex extends App {
  //easy function
  //TODO write a function (name it yourself) to calculate Fahrenheit from Celsius
  //f = 32 + c*9/5

  //test it with 36.6
  //test it with 37
  //test it with 48.5


  def celsiusToFahrenheit(celsius: Double): Double = 32 + celsius * 9 / 5

  println(celsiusToFahrenheit(36.6))
  println(celsiusToFahrenheit(37))
  println(celsiusToFahrenheit(48.5))


}
