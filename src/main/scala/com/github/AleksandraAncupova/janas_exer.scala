package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object janas_exer extends App {
  val sentence = readLine("Please enter a sentence: ").trim
  val betterSentence = sentence.replaceAll("\\p{Punct}", "") // from Java, found online
  // it only works if there are spaces between words, not only punctuation
  val words = betterSentence.split(" ")
  val wordLengths = words.map(_.length)
  val wordsOverFive = words.filter(_.length > 5)

  println(s"Word lengths: ${wordLengths.mkString(", ")}")
  println(s"The long words: ${wordsOverFive.mkString(", ")}")

}
