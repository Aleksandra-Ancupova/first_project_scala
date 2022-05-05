package com.github.AleksandraAncupova

import java.sql.DriverManager
import scala.collection.mutable.ArrayBuffer

case class Album(AlbumId: Int, Title: String, ArtistId: Int)

case class Track(TrackId: Int, Name: String, AlbumId: Int,
                 MediaTypeId: Int, GenreId: Int, Composer: String,
                 Milliseconds: Int, Bytes: Int, UnitPrice: Double)

object Day25Exercise extends App {
  val dbPath = "src/resources/db/chinook.db"
  val url = s"jdbc:sqlite:$dbPath"
  val conn = DriverManager.getConnection(url)
  val statement = conn.createStatement()

  // ALBUMS

  val sql =
    """
      |SELECT * FROM albums
      |""".stripMargin

  val resultSet = statement.executeQuery(sql)
  val metaData = resultSet.getMetaData

  for (c <- 1 to metaData.getColumnCount) {
    println(metaData.getColumnName(c))
  }

  val albumBuffer = ArrayBuffer[Album]()

  while (resultSet.next()) {
    val album = Album(resultSet.getInt("AlbumId"), resultSet.getString("Title"),
      resultSet.getInt("ArtistId"))
    albumBuffer += album
  }

  val albumCollection = albumBuffer.toArray
  albumCollection.take(5).foreach(println)

  // TRACKS

  val sql2 =
    """
      |SELECT * FROM tracks
      |""".stripMargin

  val resultSet2 = statement.executeQuery(sql2)
  val metaData2 = resultSet2.getMetaData

  for (c <- 1 to metaData2.getColumnCount) {
    println(metaData2.getColumnName(c))
  }
  val trackBuffer = ArrayBuffer[Track]()

  while (resultSet2.next()) {
    val track = Track(resultSet2.getInt("TrackId"), resultSet2.getString("Name"),
      resultSet2.getInt("AlbumId"), resultSet2.getInt("MediaTypeId"),
      resultSet2.getInt("GenreId"), resultSet2.getString("Composer"),
      resultSet2.getInt("Milliseconds"), resultSet2.getInt("Bytes"),
      resultSet2.getDouble("UnitPrice"))

    trackBuffer += track
  }

  conn.close()

  val trackCollection = trackBuffer.toArray
  trackCollection.take(5).foreach(println)

  val trackCollectionStrings = trackCollection.mkString("\n")
  trackCollectionStrings.take(5).foreach(println)

  // SAVE AS CSV
  val dst = "src/resources/csv/tracks"


}
