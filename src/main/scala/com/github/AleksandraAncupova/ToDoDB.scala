package com.github.AleksandraAncupova

import java.sql.{DriverManager, PreparedStatement}

class ToDoDB(val dbPath: String) {

  val url = s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)

  println(s"Database is opened at ${conn.getMetaData.getURL}")

  def migrate(): Unit = {
  // creating tables in DB

    val statement = conn.createStatement()

    val sql =
      """
        |CREATE TABLE IF NOT EXISTS statuses (
        |id INTEGER PRIMARY KEY,
        |status TEXT NOT NULL
        |);
        |""".stripMargin

    statement.execute(sql)

    val sql2 =
      """
        |CREATE TABLE IF NOT EXISTS tasks (
        |id INTEGER PRIMARY KEY,
        |task TEXT NOT NULL,
        |status TEXT NOT NULL,
        |created TEXT,
        |   FOREIGN KEY (status)
        |     REFERENCES statuses (id)
        |);
        |""".stripMargin

    statement.execute(sql2)

  }

  def insertNewStatus(status: String): Unit = {
    val sql =
      """
        |INSERT INTO statuses (status)
        |VALUES (?);
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, status)

    preparedStmt.execute
    preparedStmt.close()
  }


  // inserting full task info into table
 //  def insertTask(task:Array[Task])
}
