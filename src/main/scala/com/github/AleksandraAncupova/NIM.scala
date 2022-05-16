package com.github.AleksandraAncupova

import java.nio.file.{Files, Paths}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

class NIM(
           val playerA:String,
           val playerB: String,
           val startingCount:Int = 21,
           val gameEndCondition:Int = 0,
           val minMove: Int = 1,
           val maxMove: Int = 3,
           var isPlayerATurn:Boolean = true) {

  println("Created a new game object of NIM")

  var currentState:Int = startingCount
  var currentPlayer: String = if (isPlayerATurn) playerA else playerB
  var movesArray: ArrayBuffer[Int] = ArrayBuffer()

  def removeMatches(unsafeMove:Int):Int = {
    val safeMove = clampMove(unsafeMove, minMove, maxMove)
    currentState -= safeMove //so we mutate/modify internal currentState = currentState - safeMove
    movesArray += safeMove //save the move to move log
    currentState
  }

  //I am removing parameters since showStatus only cares about properties local to this object
  def showStatus():Unit = {
    if (currentState > gameEndCondition) {
      println(s"There are $currentState matches left.")
      println(s"It is $currentPlayer's turn.")
    }
    else {
      println(s"The game has ended. $currentPlayer (same as $getWinner ) has won. ")
      println(s"Better luck next time $getLoser.")
      //we could calculate the loser's name as well of course
    }
  }

  def getWinner: String = {
    if (isGameActive) "N/A" // could be empty string
    else currentPlayer //since currentPlayer with no moves to make is the winner
  }

  def getLoser: String = {
    if (isGameActive) "N/A" // could be empty string
    else { //game is finished
      if (isPlayerATurn) playerB else playerA
    }
  }

  def clampMove(move: Int, min:Int, max:Int, verbose: Boolean  = true): Int = {
    if (move > max) {
      if (verbose) println(s"$move was too much, you will have to settle for $max")
      max //return since this is the last line of the function
    } else if (move < min) {
      if (verbose) println(s"$move is too little, you will have to settle for $min")
      min //return
    } else {
      move //return
    }
  }

  /**
   * Toggles to the next player
   * @return
   */
  def nextPlayer: String = {
    isPlayerATurn = !isPlayerATurn
    currentPlayer = if (isPlayerATurn) playerA else playerB
    currentPlayer
  }

  def isCurrentPlayerComputer :Boolean = currentPlayer == "COMPUTER"

  def isGameActive: Boolean = currentState > gameEndCondition

  def printMoves :Array[Int] = {
    for ((move, index) <- movesArray.zipWithIndex) {
      val playerName = if (index % 2 == 0) playerA else playerB
      println(s"Move ${index+1}. $playerName took $move matches")
    }
    movesArray.toArray
  }

  def getMoves: Array[Int] = {
    movesArray.toArray
  }

  def saveGameResult(dst: String): Unit = {
    if (! Files.exists(Paths.get(dst))) {
      println("Saving header since no file exists")
      val header = "winner, loser, date"
      MyUtil.saveText(dst, header)
    } else {
      val timeNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now)
      val row = s"$getWinner, $getLoser, $timeNow"
      MyUtil.saveText(dst, row, append = true)
    }
  }


  def saveGameScore(folder:String = "src/resources/nim", prefix:String = "game", suffix: String = ".csv"): Unit = {
    println("Saving game score")

    val timeNow = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now)
    val dst = folder + "/" + prefix + timeNow + suffix

    //    for ((move, index) <- movesArray.zipWithIndex) {
    //      val playerName = if (index % 2 == 0) playerA else playerB
    //      val row = s"$playerName $move"
    //      MyUtil.saveText(dst, row, append = true)
    //    }
    //saving line by line will be slower

    val moveRows = for ((move, index) <- movesArray.zipWithIndex) yield {
      val playerName = if (index % 2 == 0) playerA else playerB
      val row = s"$playerName $move"
      row
    }
    MyUtil.saveLines(dst, moveRows.toArray)
  }

}
