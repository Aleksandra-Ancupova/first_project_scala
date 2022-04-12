package com.github.AleksandraAncupova

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





}
