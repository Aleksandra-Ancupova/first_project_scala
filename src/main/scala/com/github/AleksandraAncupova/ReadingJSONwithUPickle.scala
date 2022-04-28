package com.github.AleksandraAncupova

object ReadingJSONwithUPickle extends App {
  val src = "src/resources/json/fruitfacts.json"
  val rawText = MyUtil.getTextFromFile(src)
  println(rawText.take(100))

  // using uPickle library to parse the raw Text into some structure
  val data = ujson.read(rawText)
  println(data)
  val arrData = data.arr.toArray

  val fruits = for (o <- arrData) yield {
    Fruit(o("genus").str,
      o("name").str,
      o("id").num.toInt,
      o("family").str,
      o("order").str,
      o("nutritions")("carbohydrates").num, //so Double by default
      o("nutritions")("protein").num,
      o("nutritions")("fat").num,
      o("nutritions")("calories").num.toInt,
      o("nutritions")("sugar").num
    )
  }
  fruits.take(3).foreach(println)

  val calories = fruits.map(_.calories)
  val maxCalories = fruits.filter(_.calories == calories.max)
  maxCalories.foreach(println)

  println("Here are fattiest fruits:")
  val fat = fruits.map(_.fat)
  val maxFat = fruits.sortBy(_.fat)
  maxFat.reverse.take(5).foreach(println)

  println("Here are protein rich fruits:")
  val protein = fruits.map(_.protein)
  val maxProtein = fruits.sortBy(_.protein)
  maxProtein.reverse.take(5).foreach(println)

  println("Here are least sugary fruits:")
  val sugar = fruits.map(_.sugar)
  val maxSugar = fruits.sortBy(_.sugar)
  maxSugar.take(5).foreach(println)

  println("Here are 5 fruits with most carbohydrates that are not sugars:")

  val carbs = fruits.sortBy(_.nonSugar)
  carbs.reverse.take(5).foreach(println)




}
