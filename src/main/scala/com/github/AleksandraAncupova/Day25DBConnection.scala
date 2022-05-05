package com.github.AleksandraAncupova

import java.sql.DriverManager
import scala.collection.mutable.ArrayBuffer

case class Genre(genreID: Int, Name: String)

object Day25DBConnection extends App {

  val dbPath = "src/resources/db/chinook.db"
  val url = s"jdbc:sqlite:$dbPath"

  println(s"Will connect to SQlite database from here: $url")

  val conn = DriverManager.getConnection(url)

  val statement = conn.createStatement() // we create a statement object that will handle sending SQL statement to the DB


//  val sql =
//      """
//        |SELECT * FROM tracks
//        |LIMIT 20;
//        |""".stripMargin

  val sql =
    """
      |SELECT * FROM genres;
      |""".stripMargin

//  val sql =
//    """
//      |SELECT * FROM artists
//      |JOIN albums
//      |ON artists.ArtistID = albums.ArtistID;
//      |""".stripMargin

  val resultSet = statement.executeQuery(sql)
  val metaData = resultSet.getMetaData

  println(s"We have received ${metaData.getColumnCount} columns")
  for (i <- 1 to metaData.getColumnCount) {
    println(s"Column $i is named: ${metaData.getColumnName(i)}")
    println(s"Column $i comes from table: ${metaData.getTableName(i)}")
  }

  val genreBuffer = ArrayBuffer[Genre]()

  while (resultSet.next()) {
    println(resultSet.getInt(1), resultSet.getString(2))

    for (i <- 1 to metaData.getColumnCount) {
      print(resultSet.getString(i) + " ") // so getString works on Integers
    }
    val genre = Genre(resultSet.getInt("genreId"), resultSet.getString("Name"))
    genreBuffer += genre
    println()
  }

  conn.close()

  val genreCollection = genreBuffer.toArray
  genreCollection.take(5).foreach(println)
}
