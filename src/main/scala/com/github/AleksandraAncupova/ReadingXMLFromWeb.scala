package com.github.AleksandraAncupova


import java.nio.file.{Files, Paths}

object ReadingXMLFromWeb extends App {
  val url = "https://gist.github.com/Ram-N/5189462/raw/46db0b43ad7bf9cbd32a8932f3ab981bd4b4da7c/books.xml"
  val dstFolder = "src/resources/xml"
  Files.createDirectories(Paths.get(dstFolder))

  val fileName = "books.xml"
  val dst = s"$dstFolder/$fileName"

  val text = MyUtil.getTextFromWebAndSave(url,dst)
  println(text.take(500))



}
