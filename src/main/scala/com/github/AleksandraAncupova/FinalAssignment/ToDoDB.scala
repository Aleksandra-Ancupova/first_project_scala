package com.github.AleksandraAncupova.FinalAssignment

import java.sql.{DriverManager, PreparedStatement}
import scala.collection.mutable.ArrayBuffer

/**
 * class that keeps information retrieved from the DB about each task
 * @param id task ID
 * @param task short title of the task
 * @param created current timestamp
 * @param deadline date/time format entered for finished the task
 * @param status created, in progress or finished
 */
case class Task(id: Int, task: String, created:String,  deadline:String, status:String) {
  def getPrettyString: String = s"ID: $id, Task: $task, created: $created, status: $status, deadline: $deadline"
}

/**
 * class that keeps information retrieved from the DB about number of tasks is each status
 * @param status created, in progress or finished
 * @param numberOfTasks number of task rows/status
 */
case class Status (status: String, numberOfTasks: Int) {
  def statusPrettyPrint: String = s"There are $numberOfTasks tasks with status $status"
}

class ToDoDB(val dbPath: String) {

  val url = s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)

  println(s"Database is opened at ${conn.getMetaData.getURL}")

  /**
   * drops/deletes a whole table of tasks
   */
  def dropTable():Unit = {
    val statement = conn.createStatement()
    val sql =
      """
        |DROP TABLE IF EXISTS tasks;
        |""".stripMargin

    statement.execute(sql)
    }

  /**
   * creates new table of users (not used currently) and tasks in the DB
   */
  def migrate(): Unit = {
  // creating tables in DB

    val statement = conn.createStatement()

    val sql =
      """
        |CREATE TABLE IF NOT EXISTS users (
        |id INTEGER PRIMARY KEY,
        |userName TEXT NOT NULL
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
        |status TEXT NOT NULL
        |);
        |""".stripMargin

    statement.execute(sql2)

  }

  /**
   * saves user name into user table
   * @param userName name of the user
   */
  def saveUser(userName: String): Unit = {
    val sql =
      """
        |INSERT INTO users (userName)
        |VALUES (?)
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)
    preparedStmt.setString(1, userName)

    preparedStmt.execute
    preparedStmt.close()
  }

  /**
   * inserts full information about the task into DB
   * @param task short title of the task
   * @param deadline date/time format entered for finished the task
   * @param status created, in progress or finished
   */
  def insertTaskDB(task: String, deadline: String, status: String): Unit = {
    val sql =
      """
        |INSERT INTO tasks (task, created, deadline, status)
        |VALUES (?, datetime('now', 'localtime'), ?, ?)
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, task)
    preparedStmt.setString(2, deadline)
    preparedStmt.setString(3, status)

    preparedStmt.execute //not checking for success
    preparedStmt.close()

  }


  /**
   * returns all tasks that are not finished
   * @return
   */
  def getRemainingTasks:Array[Task] = {

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

  /**
   * returns all tasks that are finished
   * @return
   */
  def getFinishedTasks:Array[Task] = {

    val sql =
      """
        |SELECT * FROM tasks
        |WHERE status = "finished";
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
    taskBuffer.toArray
  }

  /**
   * returns total number of tasks in DB
   * @return
   */
  def getNumberOfTasks():Int = {
    val sql =
      """
        |SELECT COUNT(*) Number
        |FROM tasks
        |""".stripMargin

    val statement = conn.createStatement()
    val rs = statement.executeQuery(sql)
    rs.getInt("Number")
  }

  /**
   * deletes all finished tasks from the DB
   */
  def deleteFinishedTasks(): Unit = {
    val sqlDelete =
      """
        |DELETE FROM tasks
        |WHERE status = "finished"
        |""".stripMargin
    val statement = conn.createStatement()
    statement.executeUpdate(sqlDelete)
  }

  /**
   * updates task status in the DB for a provided taskID
   * @param taskID ID of the task entered by user
   * @param status status entered by user (created, in progress or finished)
   */
  def updateTaskStatusDB(taskID: Int, status: String): Unit ={
    val sql =
      """
        |UPDATE tasks
        |SET status = ?
        |WHERE id = ?;
        |""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, status)
    preparedStmt.setInt(2, taskID)

    preparedStmt.execute
    preparedStmt.close()
  }

  /**
   * gets how many rows are in each status in the DB
   * @return
   */
  def getStatsDB: Array[Status] = {
    val statement = conn.createStatement()
    val sql =
      """
        |SELECT status, COUNT(*) numberOfTasks
        |FROM tasks
        |GROUP BY status;
        |""".stripMargin

    val rs = statement.executeQuery(sql)
    val statusBuffer = ArrayBuffer[Status]()
    while (rs.next()) {
     val status = Status(rs.getString("status"),
        rs.getInt("numberOfTasks"))
     statusBuffer += status
    }
    statusBuffer.toArray
  }

  /**
   * gets not finished tasks sorted by deadline
   * @return
   */
  def sortTaskByDate(): Array[Task] = {
    val sqlSort =
      """
        |SELECT * FROM tasks
        |WHERE status != "finished"
        |ORDER BY deadline
        |LIMIT 5;
        |""".stripMargin
    val statement = conn.createStatement()
    val rs = statement.executeQuery(sqlSort)
    val taskBuffer = ArrayBuffer[Task]()
    while (rs.next()) {
      val task = Task(rs.getInt("id"),
        rs.getString("task"),
        rs.getString("created"),
        rs.getString("deadline"),
        rs.getString("status"))
      taskBuffer += task
    }
    taskBuffer.toArray
  }

}
