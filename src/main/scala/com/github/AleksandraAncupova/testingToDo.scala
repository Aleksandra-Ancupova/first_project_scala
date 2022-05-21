package com.github.AleksandraAncupova

object testingToDo extends App {

  val db = new ToDoDB("src/resources/todo/todo.db")

  db.updateTaskStatusDB(2,"finished")

}
