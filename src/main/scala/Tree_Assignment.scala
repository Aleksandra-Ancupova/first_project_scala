import scala.io.StdIn.readLine

object Tree_Assignment extends App {

  val name = readLine("Hi, what's your name?")
  val height = readLine(s"Great, $name, how tall (from 1 to 40) would you like your tree to be?").toInt

  def printTree(name:String, height:Int, maximumHeight:Int=40):Unit = {
    for (c <- 0 until height) {
      println(" " * (height - c) + "*" * c * 2 + "*")
    }
  }
  println(printTree(name,height))

// Here is my attempt to do the hard part of the exercise, but it doesn't work when height is more than name length:
  // println(" " * height + "*")
  // for (c <- 0 until height-1) {
  // val (char,index) = name.zipWithIndex(c)
    // println(" " * (height -1 - c) + s"$char" * c * 2 + s"$char" * 3)  }


}