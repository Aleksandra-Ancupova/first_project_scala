package com.github.AleksandraAncupova

import scala.xml.{Node, XML}

object ParsingXML extends App {
  val src = "src/resources/xml/books.xml"
  val xml = XML.loadFile(src)

  // println(xml)

  val myOwnXML = <book>
    <title>Best Fruit Cocktails
    </title>
  <author>
  </author></book>

  println(myOwnXML)

  val books = xml \ "book"

  val firstBook = books.head
  println(firstBook)

  println((firstBook \ "author").text)
    println((firstBook \ "title").text)
  println((firstBook \ "genre").text)
  println((firstBook \ "price").text)

  def getBook(node: Node): BookUnit = {
    val id = node.attribute("id").getOrElse("bk0").toString
    val author = (node \ "author").text
    val title = (node \ "title").text
    val genre = (node \ "genre").text
    val price = (node \ "price").text.toDouble
    val publishDate = (node \ "publish_date").text
    val description = (node \ "description").text
    BookUnit(id, author, title, genre, price, publishDate, description)
  }

  val bookUnits = (for (book <- books) yield getBook(book)).toArray

  println(bookUnits.head)

  val genres = bookUnits.map(_.genre).distinct
  println(genres.mkString(","))

  val romanceBooks = bookUnits.filter(_.genre == "Romance")
  println("Here are all romance books:")
  romanceBooks.foreach(println)

  val prices = bookUnits.map(_.price)
  val expBooks = bookUnits.sortBy(_.price)
  println(s"Here are 5 most expensive books:")
  expBooks.reverse.take(5).foreach(println)

  val authors = bookUnits.map(_.author).distinct.sorted
  println("Here are all distinct authors:")
  authors.foreach(println)

}
