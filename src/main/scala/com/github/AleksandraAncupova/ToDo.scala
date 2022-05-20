package com.github.AleksandraAncupova

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.StdIn.readLine

case class Task(id: Int, task: String, created:String,  deadline:String, status:String) {
def getPrettyString: String = s"ID: $id, Task: $task, created: $created, status: $status, deadline: $deadline"

}

class ToDo( val task: String,
            val created: String =  DateTimeFormatter.ofPattern("yyyy-MM-ddnHH:mm").format(LocalDateTime.now),
            val status: String = "created",
            val deadline: String) {

  val db = new ToDoDB("src/resources/todo/todo.db")

  def enterNewTask(): Unit = {
    val task = readLine("Enter the task: ")
    val status = "created"
    // TODO check if the deadline is not not earlier than current time
    val deadline = readLine("Enter the deadline (ENTER to skip): (yyyy-MM-dd HH:mm)")

    println(s"New ToDo is created: $task, deadline: $deadline")
    db.insertTaskDB(task, deadline, status)
  }
  def showRemainingTasks(): Unit = {

    println("Remaining tasks are:")
    val remainingTasks = db.getRemainingTasks
    //val allPlayers = getFullPlayerInfo()
    remainingTasks.foreach(task => println(task.getPrettyString))
  }

  def showFinishedTasks(): Unit = {

    println("Finished tasks are:")
    val finishedTasks = db.getFinishedTasks()
    finishedTasks.foreach(task => println(task.getPrettyString))
    println("Keep up the good work")
  }


  def updateTaskStatus(): Unit = {
    //need to show task list, so:
    showRemainingTasks()

    val chosenTask = readLine("Please enter the ID of the task to be updated:").toInt

    var updatedStatus = readLine("Choose the status:\n" +
      "(C) created\n" +
      "(P) in progress\n" +
      "(F) finished")

    if (updatedStatus.toLowerCase.startsWith("c")) updatedStatus = "created"
    else if (updatedStatus.toLowerCase.startsWith("p")) updatedStatus = "in progress"
    else updatedStatus = "finished"

    println(s"Great, task with ID $chosenTask was updated. New status is: $updatedStatus")

   db.updateTaskStatusDB(chosenTask, updatedStatus)

  }

  /**
   * leave the main loop
   */
  def quit(): Unit = {
    FinalProject_ToDo_List.userIsActive = false
    println("All is done! See you next time :)")

  }

  }