package com.github.AleksandraAncupova

import java.nio.file.{Files, Paths}

object ReadingJSONFromWeb extends App {
  val url = "https://www.fruityvice.com//api/fruit/all"

  val dstFolder = "src/resources/json"
  Files.createDirectories(Paths.get(dstFolder))

  val fileName = "fruitfacts.json"
  val dst = s"$dstFolder/$fileName"

  val text = MyUtil.getTextFromWebAndSave(url,dst)
  println(text.take(500))
}
