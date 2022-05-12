package com.github.AleksandraAncupova

import java.io.FileNotFoundException
import scala.io.StdIn.readLine

object Day26NIM extends App {

  //TODO implement basic version of https://en.wikipedia.org/wiki/Nim
  //https://en.wikipedia.org/wiki/Nim#The_21_game


  //TODO setup/config - set data/state what is needed for the application
  //TODO main application/game loop - it could be a loopless - if you process data only once
  //TODO cleanup - close database connections, files etc
  //No plan survives first contact with the enemy - who said it first?
  //It is normal (especially Agile development) to adjust as you development

  //NIM specific TODO
  //setup
  //we will start with 21 matches/tokens
  val saveDst = "src/resources/nim/scores.csv"
  val startingCount = 21
  val gameEndCondition = 0
  val minMove = 1
  val maxMove = 3

  val playerA = readLine("Player A what is your name?")
  var playerB = readLine("Player B what is your name? (press ENTER for computer) ")
  if (playerB == "") playerB = "COMPUTER" //TODO see if you can do the previos 2 lines at once


  println(s"Player A -  $playerA and Player B - $playerB let us play NIM!")

  //inevitably in most applications we will have some state that we want to keep track of
  //here it is simple enough state that we can use a few variables
  //at some point we will want to structure this game/app state into a separate object based on some class
  //  var currentState = startingCount
  val isPlayerAStarting = true //so A goes first

  //TODO create a new object holding all the information necessary for a game nim from this class Nim
  val nimGame = new NIM(playerA, playerB,startingCount, gameEndCondition, minMove, maxMove, isPlayerAStarting)

  def getComputerMove():Int = 2 //TODO add more complex logic later
  //computer can be made to play perfectly
  //or we could add some randomness

  //TODO error checking

  def getHumanMove(): Int = {
    var needsInteger = true
    var myInteger = 0
    while (needsInteger) {
      val moveInput = readLine(s"How many marches do you want to take ${nimGame.currentPlayer}? (1-3)")
      try {
        myInteger = moveInput.toInt
        needsInteger = false // this line will not execure if error is encountered
      } catch {
        case e:FileNotFoundException => println("No such file")
        case unknown => println("Got this unknown exception we need an integer!: " + unknown)
      }
    }
    myInteger
  }

  //main loop - while there are some matches play on
  //TODO implement PvP - player versus player - computer only checks the rules
  while (nimGame.isGameActive) {
    //show the game state
    //    println(s"Currently there are $currentState matches on the table")
    nimGame.showStatus()

    val move = if (nimGame.isCurrentPlayerComputer) {
      getComputerMove()
    } else {
      getHumanMove()
    }

    nimGame.removeMatches(move)
    nimGame.nextPlayer
  }
  //TODO PvC - player versus computer you will need to add some logic to the computer, add more levels

  //end cleanup here we just print some game state and congratulations


  //  val winner = if (isPlayerATurn) playerA else playerB
  //  val loser = if (!isPlayerATurn) playerA else playerB
  //  println(s"Game ended. Congratulations $winner! Better luck next time $loser.")
  nimGame.showStatus()
  nimGame.printMoves

  // TODO add saving to Database, stats etc

  // Day27NIMPersistence.saveGameResult(saveDst, nimGame.getWinner(), nimGame.getLoser())

  nimGame.saveGameResult(saveDst)
  nimGame.saveGameScore()

}
