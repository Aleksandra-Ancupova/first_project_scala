package com.github.Aleksandra.Ancupova

class Song (val title:String, val author:String, val lyrics:Seq[String]) {
  println(s"New com.github.Aleksandra.Ancupova.Song $title by $author has been made!")

  def sing(): Unit = {
    println(s"$title - $author")
    for (l <- lyrics)
    println(s"$l")
  }

def yell(): Unit = {
  println(s"$title - $author")
  for (l <- lyrics)
    println(s"${l.toUpperCase}")
}

}

  object Song_Exercise extends App {

    val ziemelmeita = new Song("Ziemeļmeita", "Jumprava", Seq("Gāju meklēt ziemeļmeitu", "Garu, tālu ceļu veicu"))
    ziemelmeita.sing()
    ziemelmeita.yell()

    println("*"*20)

    val jingleBells = new Song("Jingle Bells", "James Pierpont", Seq("Jingle bells, jingle bells", "Jingle all the way", "Oh what fun it is to ride", "In a one-horse open sleigh, hey!"))
    jingleBells.sing()
  }


