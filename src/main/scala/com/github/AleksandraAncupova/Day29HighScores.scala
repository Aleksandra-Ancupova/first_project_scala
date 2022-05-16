package com.github.AleksandraAncupova

case class Player (name:String, id: Int=0, wins: Int=0, losses: Int=0)

object Day29HighScores extends App{

  val db = new NimDB("src/resources/nim/nim.db")

  println(db.getUserId("Valdis"))
  println(db.getUserId("Alice"))
  println(db.getUserId("Liga"))

  db.insertNewUser("Shrek")
  println(db.getUserId("Shrek"))


  db.conn.close()

}
