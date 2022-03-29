class Animal (val name:String,
              val animalType:String,
              val likes:String,
              val sound:String,
              val age:Int=1) {

  def makeSound {
  println(s"I'm a $animalType and I go $sound") }

  def meet (contact:String): Unit = {
  if (contact == likes) println(s"Hi! $name likes you")
  else println("No, thanks, gotta go!")
  }
}

object Animals_Try extends App {
  val animal1 = new Animal("Carry", "Fox","Hunting","Hees")
  println (animal1.makeSound)
  println (animal1.meet("Hunting"))

  val animal2 = new Animal ("Charlotte","Cat","Sleeping","Meow")
  println (animal2.makeSound)
  println (animal2.meet("Hunting"))

  val animal3 = new Animal("Miranda","Dog","Walking","Woof")
  println (animal3.makeSound)
  println (animal3.meet("Walking"))

  val animal4 = new Animal("Samantha","Leopard","Sunbathing","Shhh")
  println (animal4.makeSound)
  println (animal4.meet("Walking"))

  }

