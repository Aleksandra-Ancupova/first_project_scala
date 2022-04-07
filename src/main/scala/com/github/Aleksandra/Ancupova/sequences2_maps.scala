package com.github.Aleksandra.Ancupova

object sequences2_maps extends App {

  val numbers = (0 to 12).toArray
  println(numbers.mkString(","))

  val squares = numbers.map(n => n * n)
  println(squares.mkString(","))


}
