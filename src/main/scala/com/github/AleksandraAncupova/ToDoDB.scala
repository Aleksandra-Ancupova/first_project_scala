package com.github.AleksandraAncupova

import java.sql.{DriverManager, PreparedStatement}
import scala.collection.mutable.ArrayBuffer

class ToDoDB(val dbPath: String) {

  val url = s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)

  println(s"Database is opened at ${conn.getMetaData.getURL}")

  def dropAllTables():Unit = {
    val statement = conn.createStatement()
    val sql =
      """
        |DROP TABLE IF EXISTS tasks;
        |""".stripMargin

    statement.execute(sql)
  }

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
        |created TEXT,
        |deadline TEXT,
        |status TEXT NOT NULL,
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
  def insertTask(task: String, deadline: String, status: String): Unit = {
    val sql =
      """
        |INSERT INTO tasks (task, created, deadline, status)
        |VALUES (?, CURRENT_TIMESTAMP, ?, ?)
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, task)
    preparedStmt.setString(2, deadline)
    preparedStmt.setString(3, status)

    preparedStmt.execute //not checking for success
    preparedStmt.close()

  }

  def getRemainingTasks():Array[Task] = {

    val sql =
      """
        |SELECT * FROM tasks
        |WHERE status != "finished";
        |""".stripMargin

    val taskBuffer = ArrayBuffer[Task]() //so we start with an empty buffer to store our rows
    val statement = conn.createStatement()
    val rs = statement.executeQuery(sql)
    while (rs.next()) {
      val task = Task(rs.getInt("id"),
        rs.getString("task"),
        rs.getString("created"),
        rs.getString("deadline"),
        rs.getString("status"))
      taskBuffer += task
    }
    taskBuffer.toArray //better to return immutable values
  }

  def updateTaskStatusDB(taskID: Int, status: String): Unit ={
    val statement = conn.createStatement()
    val sql =
      """
        |UPDATE tasks
        |SET status = ?
        |WHERE id = ?;
        |""".stripMargin

    statement.execute(sql)
  }

}
