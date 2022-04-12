package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object count_letters_map extends App {
  val text = readLine("Give me a sentence please:")
  val charCount = scala.collection.mutable.Map[Char, Int]()

  for (c <- text) {
    println(s"Will do smth with the character $c")
    charCount += (c -> text.count(t => t == c))
  }
  println(charCount)


  // second variant, faster one

  val charCountMapToo = scala.collection.mutable.Map[Char, Int]()
  for (c <- text) {
    if (charCountMapToo.contains(c)) {
      charCountMapToo(c) += 1
    } else {
      charCountMapToo(c) = 1
    }
  }
  print(charCountMapToo)
}
