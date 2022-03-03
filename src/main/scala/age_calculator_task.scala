import java.time.Year
import scala.io.StdIn.readLine

object age_calculator_task extends App{
  val myName = readLine ("What is your name?")
  println(s"Hi $myName!")
  val myAge = readLine("How old are you?") .toDouble
  val year = Year.now.getValue
  val whenHundred = year - myAge + 100
  val rounded = Math.round(whenHundred)
  println(s"Cool, you'll be 100 in $rounded")

}
