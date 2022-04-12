package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object sequence extends App {
  //TODO ask user for starting number
  //TODO ask user for ending numbers
  //Calculate cubes of these integers including start and end AND store results in a sequence
  //Print the saved sequence on screen

  //extra challenge save odd cubes and print them

  val startNumber = readLine("Hey, what's your start number?").toInt
  val endNumber = readLine("And what's your end number?").toInt

  val mySeq = (startNumber to endNumber).toList
  println(mySeq)
  val mySeqCubes = for (n <- mySeq) yield n * n * n
  println(s"Here are the cubes of my sequence:${mySeqCubes.mkString(";")}")

  val mySeqOddCubes = for (n <- mySeqCubes if n % 2 == 1) yield n
  println(s"Here are the odd cubes of my sequence:$mySeqOddCubes")

}
