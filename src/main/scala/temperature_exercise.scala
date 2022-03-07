import scala.io.StdIn.readLine

//TODO ask person for name
//Ask for their temperature
//if temperature is below 35 print "That is a bit too cold"
//if temperature is between 35 and 37 (both sides inclusive) then print "You are all right!"
//finally if the temperature is over 37 then print "You have a fever! Consider contacting a doctor"

//consider what would be the best way to handle this logic

object temperature_exercise extends App {

  val name = readLine(s"What's your name?")
  val temp = readLine(s"What's your temperature, $name?").toDouble
  if (temp<35) {
    println("That is a bit too cold") }
   else if (temp>37) {
      println("You have a fever! Please consider contacting a doctor")
    }
  else println("You are all right!")

}
