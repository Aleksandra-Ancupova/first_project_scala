package com.github.AleksandraAncupova

case class Laptop(name:String, speed:Double, manufacturer:String)

object Day16 extends App {
  println("Testing some stuff")

  val myLaptop = Laptop("Alex.laptop", 4.5, "Lenovo")
  println(myLaptop)
}
