package com.github.AleksandraAncupova

import java.io.FileNotFoundException
import scala.io.StdIn.readLine

object Day26NIM extends App {

  //TODO implement basic version of https://en.wikipedia.org/wiki/Nim
  //https://en.wikipedia.org/wiki/Nim#The_21_game

  //we will start with 21 matches/tokens
  val saveDst = "src/resources/nim/scores.csv"
  val db = new NimDB("src/resources/nim/nim.db")
  val startingCount = 21
  val gameEndCondition = 0
  val minMove = 1
  val maxMove = 3

  var playerA = readLine("Player A what is your name?")
  var playerB = readLine("Player B what is your name? (press ENTER for computer) ")
  if (playerB == "") playerB = "COMPUTER"

  var computerLevel = 0
  if (playerB == "COMPUTER") {
    computerLevel = getIntegerInput("Please enter computer level (1-3)")
  }

  def getIntegerInput(prompt: String = "Please enter an integer: "): Int = {
    var needsInteger = true
    var myInteger = 0
    while (needsInteger) {
      val moveInput = readLine(prompt)
      try {
        myInteger = moveInput.toInt
        needsInteger = false // this line will not execure if error is encountered
      } catch {
        case e: FileNotFoundException => println("No such file")
        case unknown => println("Got this unknown exception we need an integer!: " + unknown)
      }
    }
    myInteger
  }

  var isNewGameNeeded = true
  while (isNewGameNeeded) {
    println(s"Player A -  $playerA and Player B - $playerB let us play NIM!")

    val isPlayerAStarting = true //so A goes first

    val nimGame = new NIM(playerA, playerB, startingCount, gameEndCondition, minMove, maxMove, isPlayerAStarting)

    //main loop - while there are some matches play on
    while (nimGame.isGameActive) {
      nimGame.showStatus()

      val move = if (nimGame.isCurrentPlayerComputer) {
        NimAI.getComputerMove(nimGame.currentState, computerLevel)
      } else {
        getIntegerInput(s"${nimGame.currentPlayer} please enter how many matches you take (1-3)")
      }
      nimGame.removeMatches(move)
      nimGame.nextPlayer
    }

    nimGame.showStatus()
    nimGame.printMoves

    // TODO add saving to Database, stats etc

    // Day27NIMPersistence.saveGameResult(saveDst, nimGame.getWinner(), nimGame.getLoser())

    nimGame.saveGameResult(saveDst)
    nimGame.saveGameScore()

    db.insertResult(nimGame.getWinner, nimGame.getLoser)
    db.insertFullScore(nimGame.getMoves)

    db.printTopPlayers()
    db.printTopLosers()
    db.printAllPlayers()

    val nextGameInput = readLine("Do you want to play another game? (Y/N)")

    if (nextGameInput.toLowerCase.startsWith("y")) {
      isNewGameNeeded = true
    val arePlayersDifferent = readLine("Do you want to change players? (Y/N)")

    if (arePlayersDifferent.toLowerCase.startsWith("y")) {
      playerA = readLine("Player A what is your name?")
      playerB = readLine("Player B what is your name? (press ENTER for computer) ")
      if (playerB == "") {
        playerB = "COMPUTER"
        computerLevel = getIntegerInput("Please enter computer level (1-3)")
      }
    }
    } else isNewGameNeeded = false
  }


  //TODO add support for new player names
  //TODO add changing of computer level if player is computer

  println("Thank you for playing! Hope to see you again :)")

}
