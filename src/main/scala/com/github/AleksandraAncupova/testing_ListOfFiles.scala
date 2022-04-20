package com.github.AleksandraAncupova

object testing_ListOfFiles extends App{

  val fileLines = MyUtil.getLinesFromFile("src/resources/webPages2.txt")

 fileLines.foreach(println)

  var dst = "src/resources/texts/testing2"

 for (file <- fileLines) MyUtil.getTextFromWebAndSave(file, dst)



 // for (file <- fileLines) MyUtil.getTextFromWebAndSave(file,"src/resources/" )


}
