package com.github.AleksandraAncupova

import java.nio.file.{Files, Paths}

object GetCSVFileFromWeb extends App {

  val url = "https://assets.publishing.service.gov.uk/government/uploads/system/uploads/attachment_data/file/1069435/fruitvegprices-19apr22.csv"

  val dstFolder = "src/resources/csv"
  Files.createDirectories(Paths.get(dstFolder))

  val fileName = url.split("/").last
  val dst = s"$dstFolder/$fileName"

  val text = MyUtil.getTextFromWebAndSave(url,dst)
  println(text.take(100))

}
