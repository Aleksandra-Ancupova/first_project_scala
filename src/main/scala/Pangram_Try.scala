object Pangram_Try  extends App {
    //TODO write a pangram tester
    //https://en.wikipedia.org/wiki/Pangram
    //check if text contains every letter of an alphabet at least once
    //for this task consider upper and lower case the same
   // def isPangram(text:Set[Char], alpha:Set[Char]="abcdefghijklmnopqrstuvwxyz": Set[Char]):Boolean = { }
  false //FIXME }

  val alphabet = "abcdefghijklmnopqrstuvwxyz".toSet
  println(alphabet)

  val myTestText = "The five boxing wizards jump quickly.".toSet
  println(myTestText)

  val intersection = myTestText & alphabet
  println(intersection)

  println(s"Does $myTestText contain all of the $alphabet? ${alphabet.subsetOf(intersection)}")


  // println(isPangram(myTestText)) //should be true

}
