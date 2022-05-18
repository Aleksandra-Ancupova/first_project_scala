package com.github.AleksandraAncupova

import java.sql.{DriverManager, PreparedStatement}
import scala.collection.mutable.ArrayBuffer

class NimDB(val dbPath: String) {

  val url = s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)
  println(s"Opened Database at ${conn.getMetaData.getURL}")

  /**
   * Perform table migartion in a new installation, does nothing otherwise
   */

  def dropAllTables(): Unit = {
    val statement = conn.createStatement()
    val sql =
    """
      |DROP TABLE IF EXISTS results;
      |DROP TABLE IF EXISTS scores;
      |DROP TABLE IF EXISTS users;
      |""".stripMargin
    val sql2 =
    """
      |DROP TABLE IF EXISTS scores;
      |""".stripMargin
    val sql3 =
    """
      |DROP TABLE IF EXISTS users;
      |""".stripMargin

    statement.addBatch(sql)
    statement.addBatch(sql2)
    statement.addBatch(sql3)

    statement.executeBatch()
  }

  def migrate(): Unit = {
    // migrate for db refers to table creation
    val statement = conn.createStatement()

    val sql0 =
      """
        |CREATE TABLE IF NOT EXISTS users (
        |id INTEGER PRIMARY KEY,
        |name TEXT NOT NULL,
        |email TEXT,
        |created TEXT
        |);
        |""".stripMargin

    statement.addBatch(sql0)

    val sql =
      """
        |CREATE TABLE IF NOT EXISTS results (
        |id INTEGER PRIMARY KEY,
        |winner INTEGER NOT NULL,
        |loser INTEGER NOT NULL,
        |created TEXT,
        |   FOREIGN KEY (winner)
        |     REFERENCES users (id),
        |   FOREIGN KEY (loser)
        |     REFERENCES users (id)
        |);
        |""".stripMargin

    // statement.execute(sql)
    statement.addBatch(sql)

    val sql2 =
      """
        |CREATE TABLE IF NOT EXISTS scores (
        |id INTEGER PRIMARY KEY,
        |game_id INTEGER NOT NULL,
        |turn INTEGER NOT NULL,
        |move INTEGER NOT NULL,
        |created TEXT,
        |   FOREIGN KEY (game_id)
        |     REFERENCES results (id)
        |);
        |""".stripMargin

    // statement.execute(sql2)
  statement.addBatch(sql2)

  statement.executeBatch()

  }

  def insertResult(winner: String, loser: String): Unit = {
    // we want to avoid inserting unprepared values

    val insertSql = """
                      |INSERT INTO results (winner,loser,created)
                      |values (?,?,CURRENT_TIMESTAMP)
""".stripMargin

    val winnerId = insertNewUser(winner)
    val loserId = insertNewUser(loser)
    val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)

    preparedStmt.setInt(1, winnerId)
    preparedStmt.setInt(2, loserId)
    preparedStmt.execute

    preparedStmt.close()
  }

  def insertScore(game_id: Int, turn: Int, moves: Int): Unit = {
    val insertSql = """
                      |INSERT INTO scores (game_id,turn,move,created)
                      |values (?,?,?,CURRENT_TIMESTAMP)
""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)

    preparedStmt.setInt(1, game_id)
    preparedStmt.setInt(2, turn)
    preparedStmt.setInt(3, moves)

    preparedStmt.execute

    preparedStmt.close()
  }

  def insertFullScore(moves: Array[Int]): Unit = {
    val id = getIdOfLastGame()
    for ((move, turn) <- moves.zipWithIndex) {
      insertScore(id, turn, move)
    }

  }

  def getIdOfLastGame():Int = {
    val statement = conn.createStatement()
    val sql =
      """
        |SELECT MAX(id) id FROM results;
        |""".stripMargin

    val resultSet = statement.executeQuery(sql)
    val lastGameId = resultSet.getInt("id")
    lastGameId
  }

  /**
   * returns user count if it exists or 0 otherwise
   * @param name - user name
   * @return 1 or 0
   */

  def getUserCount(name: String): Int = {
    val sql =
      """
        |SELECT COUNT(*) cnt FROM users
        |WHERE name = ?;
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, name)
    val rs = preparedStmt.executeQuery()
    val cnt = rs.getInt(1)
    preparedStmt.close()
    cnt
  }


  /**
   * returns user ID if it exists or 0 otherwise
   * @param name - user name
   * @return 0 or id
   */
  def getUserId(name: String): Int = {
    if (getUserCount(name) == 0) 0 else {
      val sql =
        """
          |SELECT id cnt FROM users
          |WHERE name = ?
          |LIMIT 1;
          |""".stripMargin

      val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

      preparedStmt.setString(1, name)
      val rs = preparedStmt.executeQuery()
      val id = rs.getInt(1)
      preparedStmt.close()
      id
    }
  }

  /**
   * inserts new user and returns id of new or existing user
   * @param name - user name
   * @return id
   */
  def insertNewUser(name: String): Int = {
    if (getUserCount(name) == 0) {
      val sql =
        """
          |INSERT INTO users (name, created)
          |VALUES (?, CURRENT_TIMESTAMP);
          |""".stripMargin
      val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

      preparedStmt.setString(1, name)
      preparedStmt.execute
      preparedStmt.close()
    } else {
      println(s"User $name already exists!")
    }
    getUserId(name)
  }

  def getTopWinners(): Array[Player] = {
    val sql = """
      |SELECT u.name, COUNT(winner) wins FROM results r
      |JOIN users u
      |ON u.id = r.winner
      |GROUP BY winner
      |ORDER BY wins DESC
      |;
      |""".stripMargin

    val playerBuffer = ArrayBuffer[Player]() // so we start with an empty buffer to store our rows
    val statement = conn.createStatement()
    val rs = statement.executeQuery(sql)
    while(rs.next()) {
      val player = Player(rs.getString("name"), wins = rs.getInt("wins"))
      playerBuffer += player
    }
    playerBuffer.toArray  // better to return immutable values
  }

  def printTopPlayers(): Unit = {
    println("The Players with most wins are:")
    val topPlayers = getTopWinners()
    topPlayers.foreach(println)
  }

  def getTopLosers(): Array[Player] = {
    val sql = """
                |SELECT u.name, COUNT(loser) losses FROM results r
                |JOIN users u
                |ON u.id = r.loser
                |GROUP BY loser
                |ORDER BY losses DESC;
                |;
                |""".stripMargin

    val playerBuffer = ArrayBuffer[Player]() // so we start with an empty buffer to store our rows
    val statement = conn.createStatement()
    val rs = statement.executeQuery(sql)
    while(rs.next()) {
      val player = Player(rs.getString("name"), losses = rs.getInt("losses"))
      playerBuffer += player
    }
    playerBuffer.toArray  // better to return immutable values
  }

  def printTopLosers(): Unit = {
    println("The players with most losses are:")
    val topPlayers = getTopLosers()
    topPlayers.foreach(println)
  }

  def getFullPlayerInfo(): Array[Player] = {
    val winners = getTopWinners()
    val losers = getTopLosers()
    // for efficiency we create wto maps of name to actual Player
    val winnerMap = winners.map(winner => (winner.name, winner)).toMap // so we have acces
    val loserMap = losers.map(loser => (loser.name, loser)).toMap
    val playersBuffer = ArrayBuffer[Player]()
    for (winner <- winners) {
      val losses = if (loserMap.contains(winner.name)) loserMap(winner.name).losses else 0
      val id = getUserId(winner.name)
      playersBuffer += Player(winner.name, id, winner.wins, losses)
    }
   for (loser <- losers) {
     if (!winnerMap.contains(loser.name)) {
       val id = getUserId(loser.name)
       playersBuffer += Player(loser.name, id, 0, loser.losses)
   }
   }
    playersBuffer.toArray
  }

  def printAllPlayers(): Unit = {
    println("Full player info:")
    val allPlayers = getFullPlayerInfo()
    allPlayers.foreach(player => println(player.getPrettyString))

  }
}
