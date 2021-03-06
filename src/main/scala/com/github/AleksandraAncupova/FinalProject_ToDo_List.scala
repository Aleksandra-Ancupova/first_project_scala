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


      // val db = new ToDoDB("src/resources/todo/todo.db")  = looks like we don't need it here?

      val toDo = new ToDo("", "","","") //TODO check if ToDo class needs parameters

      var userIsActive = true

    println("Hi there!\nThis is a Task List Manager")



      /**
       * main loop
       */

      def setup(): Unit = {
        while (userIsActive) {
          val function =
            readLine("\nWhat would you like to do (choose an option please):\n" +
            "(E) enter a new task\n" +
            "(U) update status of existing task\n" +
            "(S) print some stats\n" +
            "(Q) nothing, just quit\n")

              val input = function.toLowerCase

              input match {
                case "e" => toDo.enterNewTask()
                case "u" => toDo.updateTaskStatus()
                case "s" => statsOption()
                case "q" => toDo.quit()
                case _ => println("Please choose one of the options above!")
              }
            }
          }

      def statsOption(): Unit = {
        val input2 = readLine("\nWhich stats would you like to see (choose an option please):\n" +
          "(R) show remaining tasks\n" +
          "(F) show finished tasks\n" +
          "(S) print status summary\n" +
          "(H) show 5 most urgent tasks\n" +
          "(Q) nothing, want to quit\n")

        input2 match {
          case "r" => toDo.showRemainingTasks()
          case "f" => toDo.showFinishedTasks()
          case "s" => toDo.printStats()
          case "h" => toDo.showUrgentTasks()
          case "q" => toDo.quit()
          case _ => println("Please choose one of the options above!")
        }
      }


      setup()




  }


