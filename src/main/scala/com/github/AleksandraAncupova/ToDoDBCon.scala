package com.github.AleksandraAncupova

import java.nio.file.{Files, Paths}


object ToDoDBCon extends App {

  // create directory
  val dstFolder = "src/resources/todo"
  Files.createDirectories(Paths.get(dstFolder))

  // create DB
  val db = new ToDoDB("src/resources/todo/todo.db")

  // create tables in DB

 // db.dropAllTables()
  db.migrate()

  // checking if it works
  db.insertNewStatus("started")

  //cleanup
  db.conn.close()





}
