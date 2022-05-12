package com.github.AleksandraAncupova

import java.sql.{DriverManager, PreparedStatement}

class NimDB(val dbPath: String) {

  val url = s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)
  println(s"Opened Database at ${conn.getMetaData.getURL}")

  /**
   * Perform table migartion in a new installation, does nothing otherwise
   */

  def migrate(): Unit = {
    // migrate for db refers to table creation
    val statement = conn.createStatement()
    val sql =
      """
        |CREATE TABLE IF NOT EXISTS results (
        |id INTEGER PRIMARY KEY,
        |winner TEXT NOT NULL,
        |loser TEXT NOT NULL,
        |created TEXT
        |);
        |""".stripMargin

    statement.execute(sql)

    val sql2 =
      """
        |CREATE TABLE IF NOT EXISTS scores (
        |id INTEGER PRIMARY KEY,
        |game_id INTEGER NOT NULL,
        |turn INTEGER NOT NULL,
        |move INTEGER NOT NULL,
        |created TEXT
        |);
        |""".stripMargin

    statement.execute(sql2)
  }

  def insertResult(winner: String, loser: String): Unit = {
    // we want to avoid inserting unprepared values

    val insertSql = """
                      |INSERT INTO results (winner,loser,created)
                      |values (?,?,CURRENT_TIMESTAMP)
""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)

    preparedStmt.setString(1, winner)
    preparedStmt.setString(2, loser)
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

}
