package com.github.Aleksandra.Ancupova

import scala.io.StdIn.readLine

object mapsExercise extends App {
  //TODO ask user to enter a sentence  I live in Liepaja
  //Split sentence into words using split , you will will have a sequence of words, we did this on Day 8
  //Generate word length sequence (can use map or yield)
  //Filter only words of length over 5
  //print word lengths for every word
  //print the long words

  //you are allowed to use yield or map/filter
  val sentence = readLine("Hey, enter any sentence you like!")
  println(sentence)

  val words = sentence.split(" ")
  println(words.mkString(","))
  val wordsSeq = words.toSeq
  val wordLength = wordsSeq.map(n => n.length)
  println(wordLength.mkString(",")) //sequence of word lengths

  val filteredWords = words.filter(n => n.length > 5)
  println(filteredWords.mkString(","))

  // val longWords = for (w <- words if wordLength > 5) {
  // println(s"here are you your Long words: $longWords") }
}
