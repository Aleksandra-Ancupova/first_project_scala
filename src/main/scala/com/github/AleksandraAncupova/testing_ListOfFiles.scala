package com.github.AleksandraAncupova

import java.util.Calendar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object testing_ListOfFiles extends App{

  val fileLines = MyUtil.getLinesFromFile("src/resources/webPages2.txt")

 fileLines.foreach(println)

 val timestamp = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm").format(LocalDateTime.now)
   println(timestamp)





 // for (file <- fileLines) MyUtil.getTextFromWebAndSave(file,"src/resources/" )


}
