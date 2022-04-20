package com.github.AleksandraAncupova

object testing_reading extends App{
  // val url = "https://gutenberg.org/files/902/902-0.txt"
  val url = MyUtil.getLinesFromFile("src/resources/webPages.txt").mkString
  val dst = "src/resources/texts/First_Story2"

  val myFirstStory2 = MyUtil.getTextFromWebAndSave(url,dst)
  val myFirstStory2Lines = MyUtil.getLinesFromFile(dst)

  val author = GutenbergUtil.getAuthor(myFirstStory2Lines)
  val title = GutenbergUtil.getTitle(myFirstStory2Lines)

  val modifiedStory = Array[String](s"URL: $url", s"Author: $author", s"Title: $title", "\n" * 3, myFirstStory2)

  MyUtil.saveLines("src/resources/First_Story_Modified", modifiedStory)

  MyUtil.saveLines("src/resources/" + s"$author" + "_" + s"$title", modifiedStory)
 // def save (story:Array[String]):String = {}





}
