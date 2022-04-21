package com.github.AleksandraAncupova

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Document (title:String = "",
                author:String = "",
                url:String = "",
                rows:Array[String] = Array[String]()) {

  val rowCount:Int = rows.length
  val wordCount:Int = MyUtil.getWordCountPerLine(rows).sum

  println(s"New document $title is added with $rowCount lines!")
  println(s"New document $title is added with $wordCount words in it!")
  }

  object DocumentReadingExercise extends App {

  val source = "src/resources/webPages2.txt"
  val fileLines = MyUtil.getLinesFromFile(source)

  def getDocumentsFromUrls(files:Array[String]): Array[Document] = {
      val documentArray = for (file <- files) yield {
        val text = MyUtil.getTextFromWebAndSave(file, "src/resources/temp")
        val rows = MyUtil.getLinesFromFile("src/resources/temp")
        val title = GutenbergUtil.getTitle(rows)
        val author = GutenbergUtil.getAuthor(rows)
        val url = file.mkString
        Document(title, author, url, rows)
      }
    documentArray
   }

    val filledArray = getDocumentsFromUrls(fileLines)

    def save(dst:String = "", folder:String = "src/resources/texts"):Unit = {
      for (eachArray <- filledArray) {
        Thread.sleep(200)
          val modifiedDocument = Array[String](s"URL: ${eachArray.url}",
          s"Author: ${eachArray.author}",
          s"Title: ${eachArray.title}",
          "\n" * 3)

        val finalDocument = modifiedDocument ++ eachArray.rows

        val timestamp = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm").format(LocalDateTime.now)

        MyUtil.saveLines(s"$folder" + "/" + s"${eachArray.author.take(10)}" + "_" + s"${eachArray.title.take(15)}"
          + "_" + timestamp + ".txt", finalDocument)

      }
    }

    save()

}
