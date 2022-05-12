package com.github.AleksandraAncupova

object Day28DatabaseMigration extends App {

  val db = new NimDB("src/resources/nim/nim.db")

  db.migrate()

  // db.insertResult("Alice", "Bob")
  // db.insertResult("Carol", "Dave")

  db.insertScore(1,1,5)

  db.conn.close()
}
