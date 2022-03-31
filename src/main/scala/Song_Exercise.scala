import Song_Exercise.ziemelmeita

class Song (val title:String, val author:String, val lyrics:Array[String]) {
  println(s"New Song $title by $author has been made!")

  def sing(): Unit = {
    println(s"$title - $author")
    println(s"${lyrics.mkString}")

  }

}

  object Song_Exercise extends App {

    val ziemelmeita = new Song("Ziemeļmeita", "Jumprava", Array("Gāju meklēt ziemeļmeitu", "Garu, tālu ceļu veicu"))
    ziemelmeita.sing()

    val jingleBells = new Song("Jingle Bells", "James Pierpont", Array("Jingle bells, jingle bells", "Jingle all the way", "Oh what fun it is to ride", "In a one-horse open sleigh, hey!"))
    jingleBells.sing()
  }


