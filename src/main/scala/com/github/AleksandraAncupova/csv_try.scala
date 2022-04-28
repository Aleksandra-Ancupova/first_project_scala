package com.github.AleksandraAncupova

case class Veggie (category: String,item:String ,variety: String,date:String,price:Double,unit:String)

object csv_try extends App {

  val src = "src/resources/csv/fruitvegprices-19apr22.csv"
  val lines = MyUtil.getLinesFromFile(src)

  val splitLines = for (line <- lines) yield line.split(",")

  def arrayToVeggie(array:Array[String]):Veggie = {
    Veggie(array(0), array(1), array(2), array(3), array(4).toDouble, array(5))
  }

  val veggies = splitLines.tail.map(arrayToVeggie)

  val appleEntries = veggies.filter(_.item == "apples")

  // TODO get the top 5 most expensive apple entries
  val topExpApples = appleEntries.sortBy(_.price)
  topExpApples.reverse.take(5).foreach(println)

  //TODO get the least expensive 5 apple entries
  topExpApples.take(5).foreach(println)

  //TODO get average price for lettuce
  val lettuceEntries = veggies.filter(_.item == "lettuce")
   val lettuceUnits = lettuceEntries.map(_.unit)
  lettuceUnits.distinct.foreach(println)

  val lettuceHead = lettuceEntries.filter(_.unit == "head")
  val lettuceHeadPrices = lettuceHead.map(_.price)
  println(lettuceHeadPrices.mkString(" | "))

  val lettuceHeadAveragePrice = lettuceHeadPrices.sum/lettuceHead.length
  val roundedAverageHead = MyUtil.myRound(lettuceHeadAveragePrice, 2)
  println(roundedAverageHead)

  val lettuceTwin = lettuceEntries.filter(_.unit == "twin")
  val lettuceTwinPrices = lettuceTwin.map(_.price)
  println(lettuceTwinPrices.mkString(" | "))

  val lettuceTwinAveragePrice = lettuceTwinPrices.sum/lettuceTwin.length
  val roundedAverageTwin = MyUtil.myRound(lettuceTwinAveragePrice,2)
  println(roundedAverageTwin)




}
