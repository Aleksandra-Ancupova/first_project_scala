package com.github.AleksandraAncupova

import scala.util.Random

object NimAI {

  def getComputerMove(currentState: Int, computerLevel: Int = 0): Int = {
    computerLevel match {
      case 1 => getMinimalStrategy
      case 2 => getRandomStrategy
      case 3 => getSmartStrategy(currentState)
      case _ => getMinimalStrategy  // could add error message
    }
  }

  def getMinimalStrategy: Int = 1

  def getRandomStrategy: Int = Random.nextInt(3)+1

  def getSmartStrategy(currentState: Int): Int = {
  val remainder: Int = currentState % 4
    remainder match {
      case 0 => 3
      case 1 => getRandomStrategy // so in case we are losing, we do random
      case 2 => 1
      case 3 => 2
    }
  }


}
