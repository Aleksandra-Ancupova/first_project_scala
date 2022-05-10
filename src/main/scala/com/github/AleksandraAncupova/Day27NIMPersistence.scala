package com.github.AleksandraAncupova

import java.nio.file.{Files, Paths}
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneOffset, ZonedDateTime}
import java.util.Calendar

object Day27NIMPersistence {

  def saveGameResult(dst:String, winner:String, loser:String): Unit = {
    if (! Files.exists(Paths.get(dst))) {
      println("Saving header since no file exists")
      val header = "winner,loser,date"
      MyUtil.saveText(dst, header)
    } else {
      println(s"Need to save winner $winner and loser $loser")
      val now = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm").format(LocalDateTime.now)
      println(s"Today is $now")

      // val utcNow = LocalDateTime.now.format(DateTimeFormatter.ISO_INSTANT)

      val row = s"$winner, $loser, $now"

      MyUtil.saveText(dst, row, true)
    }
  }

}
