package com.github.AleksandraAncupova

import com.github.AleksandraAncupova.testing_ListOfFiles.fileLines

case class Document (title:String = "",
                author:String = "",
                url:String = "",
                rows:Array[String] = Array[String]()) {

  val rowCount:Int = rows.length
  val wordCount:Int = MyUtil.getWordCountPerLine(rows).sum

  println(s"New document is added with $rowCount lines!")
  println(s"New document is added with $wordCount words in it!")}

  object DocumentReadingExercise extends App {

  val fileLines = MyUtil.getLinesFromFile("src/resources/webPages2.txt").mkString


  def getDocumentsFromUrls(files:Array[String]): Array[Document] = {
      val documentArray = for (file <- files) yield {
        val text = MyUtil.getTextFromWeb(file)
        val rows = MyUtil.getLinesFromFile(text)
        val title = GutenbergUtil.getTitle(rows)
        val author = GutenbergUtil.getAuthor(rows)
        val url = file.mkString
        Document(title, author, url, rows)
      }
    documentArray
   }

    println(fileLines)



  // def save(dst:String = "", folder:String = "src/resources/texts"):Unit = { }

}
