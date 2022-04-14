package com.github.AleksandraAncupova

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object ReadPoem_Exercise extends App {

  val relativeFilePath = "src/resources/stopping_by.txt"

  for (line <- Source.fromFile(relativeFilePath).getLines) println(line)

  println("*"*20)

  val myLines = Source.fromFile(relativeFilePath).getLines.toArray

  val poemName = myLines(0)
  println(poemName)

  println("*"*20)

  val poetName = myLines(1).drop(3)
  println(poetName)

  println("*"*20)

  val woodLines = myLines.filter(_.contains("woods"))
  woodLines.foreach(println)

  val outputString = myLines.head +
    "\n" +
    poetName +
    "\n" +
    woodLines.mkString("\n")

  val stringBuilder = new StringBuilder()
  stringBuilder ++= myLines.head
  stringBuilder ++= "\n" //for single characters  of course ++= "\n" would work as well
  stringBuilder ++= poetName
  stringBuilder ++= "\n"
  stringBuilder ++= woodLines.mkString("\n")

  println("We built a string, this is recommended for longer texts")
  println(stringBuilder.mkString)

  MyUtil.saveText("src/resources/woods.txt", outputString)

  val myBuffer = ArrayBuffer[String]()
  myBuffer += myLines.head
  myBuffer += poetName
  myBuffer ++= woodLines

  MyUtil.saveLines("src/resources/woods_arr.txt", myBuffer.toArray)

  MyUtil.saveLines("src/resources/woods_arr.txt", Array("*"*30, "Hurray", "hurray"), append = true)
  MyUtil.saveLines("src/resources/woods_arr.txt",
    Array("*"*30, "aha", "oho"), append = true, lineEnd = "\n😅\n")



}
