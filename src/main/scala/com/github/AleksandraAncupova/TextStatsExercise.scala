package com.github.AleksandraAncupova

object TextStatsExercise extends App {
    //TODO download and save a text file of your choosing from gutenberg.org
    //Get Character Count (including whitespace)
    //Get Line Count
    //get Word Count
    //Get average Word Count in each text line

    //You can use the Util functions or maybe add some of your own if you want

  val url = "https://gutenberg.org/cache/epub/2295/pg2295.txt"
  val dst = "src/resources/O.Henry_Short_Stories"
  val text = MyUtil.getTextFromWebAndSave(url, dst)

  val linesOfText = MyUtil.getLinesFromFile(dst)

  val lineCount = linesOfText.length
  println(s"This is how many lines are in a set of O.Henry stories: $lineCount")

  val wordCount = MyUtil.getWordCountPerLine(linesOfText).sum
   println(s"This is how many words are in a set of O.Henry stories: $wordCount")

  val charCount = MyUtil.getCharacterCount(linesOfText)
  println(s"This is how many characters are in a set of O.Henry stories: $charCount")

  val averageWordCount = wordCount*1.0 / lineCount
  val roundedAverageWordCount = MyUtil.myRound(averageWordCount, 2)
  println(s"The average number of words in each line of text is: $roundedAverageWordCount")

}
