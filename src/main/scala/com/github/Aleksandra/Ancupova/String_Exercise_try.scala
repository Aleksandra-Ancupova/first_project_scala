package com.github.Aleksandra.Ancupova

object String_Exercise_try extends App {
  //TODO
  def processString(text: String, uppercaseChars: String = "", needsTrim: Boolean = false): String = {
    //TODO first trim string if it needs trimming from the argument
    //TODO replace All characters in uppercaseChars with their uppercase versions
    //you will need to write a loop
    //you will probably want to use var to store a temporary string that you keep reweriting
    //return newly created string


    var trimmedString = ""
    if (needsTrim) {
      trimmedString = text.trim
    } else {
      trimmedString = text
    }

    var newString = ""
    for (c <- trimmedString) {
      if (uppercaseChars.contains(c)) {
        newString += c.toUpper
      } else {
        newString += c
      }
    }
    newString
  }

  println(processString("abracadabra", "cr")) //should print abRaCadabRa
  println(processString("   abracadabra  ", "cr", needsTrim = true)) //should print abRaCadabRa

}
