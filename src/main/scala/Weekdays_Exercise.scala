object Weekdays_Exercise extends App {

  def getDay(day:Int): String = day match {
    case 1 => "Monday"
    case 2 => "Tuesday"
    case 3 => "Wednesday"
    case 4 => "Thursday"
    case 5 => "Friday"
    case 6 => "Saturday"
    case 7 => "Sunday"
    case _ => "Unknown Weekday"

  }
  println(getDay(2))
  println(getDay(4))
  println(getDay(43))

  def getDayType(day: String): String = day.toLowerCase match {
    case "monday" | "tuesday" | "wednesday" | "thursday" | "friday" => "Weekday"
    case "saturday" | "sunday" => "Weekend"
    case _ => "Not in a calendar :("
  }

  println(getDayType("Monday"))
  println(getDayType("monday"))
  println(getDayType("SUNDAY"))
  println(getDayType("sAtuRday"))
  println(getDayType("siiilly"))



}
