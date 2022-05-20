package com.github.AleksandraAncupova

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.StdIn.readLine



     // set up connection to database when we run application, new folder in resources is created and inside is database todo.db
    // connect DBeaver to this database, then all entries are written there
    // ask user for tasks, write it one by one

    // table tasks should have columns id, Task, Created, Status, Finished ? maybe priority?
    // another table could have statuses: id, Started, In Progress, Done
    // maybe account for several users? then each user has separate table of tasks

    // App can ask you smth like:
    // Would you like to add a task or update status?
    // Which task would you like to add to your TODO list?
    // Another?
    // Update status?


    // maybe then create method to get some stats on completed tasks, etc?

    object FinalProject_ToDo_List extends App {

      var userIsActive = true
      val db = new ToDoDB("src/resources/todo/todo.db")

      val toDo = new ToDo("", "","","") //TODO check if ToDo class needs parameters


    println("Hi there!\nThis is a Task List Manager")

      /**
       * main loop
       */

      def setup(): Unit = {
        while (userIsActive) {
          val function = readLine("What would you like to do (choose an option please):\n" +
            "(E) enter a new task\n" +
            "(U) update status of existing task\n" +
            "(R) show remaining tasks\n" +
            "(Q) quit\n")

          if (function.toLowerCase.startsWith("e")) {
            toDo.enterNewTask()
          }
          else if (function.toLowerCase.startsWith("u")) {
            toDo.updateTaskStatus()
          }
          else if (function.toLowerCase.startsWith("r")) {
            toDo.showRemainingTasks()
          }
          else {
            toDo.quit()
            userIsActive = false
          }
        }
      }
      setup()



  }


