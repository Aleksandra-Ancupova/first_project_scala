package com.github.AleksandraAncupova

case class Fruit(genus: String,
                 name: String,
                 id: Int,
                 family: String,
                 order: String,
                 carbohydrates: Double = 0.0,
                 protein: Double = 0.0,
                 fat: Double = 0.0,
                 calories: Double = 0.0,
                 sugar: Double = 0.0) {
  val nonSugar = carbohydrates - sugar
}

