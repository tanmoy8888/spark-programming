package oop.caseclass

/**
 * @author Tanmoy Mukherjee
 */
object MyCaseClassClient {
  def main(args: Array[String]): Unit = {
    val c = MyCaseClass("Tanmoy","Mukherjee",1234,24)
    println(s"output of my case class $c")
    println(s"Example of Product Element :: First Element , ${c.productElement(0)}")
    println(s"Example of Product Element :: Second Element , ${c.productElement(1)}")
    println(s"Example of Product Element :: Third Element , ${c.productElement(2)}")
    println(s"Example of Product Element :: Forth Element , ${c.productElement(3)}")
    println(s"Product prefix :: ${c.productPrefix}")
    println(s"productArity :: ${c.productArity}")
    println(s"productIterator ------------------- ")
    c.productIterator.foreach(println)
  }

}
