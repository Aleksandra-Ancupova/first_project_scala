package com.github.AleksandraAncupova

trait FlyingTrait {
  def fly(): Unit
}

trait RunningTrait {
  def run(speed:Int): Unit
}

trait SwimmingTrait {
  def swim(): Unit
}

class Penguin (val penguinName: String) extends SwimmingTrait with RunningTrait {
  def swim(): Unit = println(s"$penguinName can swim very swiftly")
  def run(speed:Int): Unit = println(s"$penguinName is running kinda funny")
}
class Chicken (val chickenName: String) extends FlyingTrait with RunningTrait {
  def fly(): Unit = println(s"$chickenName can hardly fly away...")
  def run(speed:Int): Unit = println(s"$chickenName can definitely run quite fast, at $speed km/h!")
}

object Birds_Exercise extends App {
  println("Let's create some birds using traits!")

  val penguin1 = new Penguin("Claus")
  penguin1.swim()
  penguin1.run(2)
  val penguin2 = new Penguin("Bert")
  penguin2.swim()


  val chicken1 = new Chicken("Henny")
  chicken1.run(7)
  val chicken2 = new Chicken("Penny")
  chicken2.run(10)
}