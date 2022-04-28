package com.github.AleksandraAncupova

object regex_try extends App{

  val src = "https://www.gutenberg.org/files/61262/61262-0.txt"
  val dst = "src/resources/Poirot_Investigates"

  val text = MyUtil.getTextFromWebAndSave(src,dst)

  val dateRedEx = raw"\b(\d{4})\b".r

  val allYears = for (y <- dateRedEx.findAllMatchIn(text)) yield y.group(1)

  allYears.foreach(println)


}
