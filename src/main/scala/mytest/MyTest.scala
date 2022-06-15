package mytest

/**
 * @author Tanmoy Mukherjee
 */
object MyTest {
  def main(args: Array[String]): Unit = {
    val element1 = Option(Array(1, 2, 3, 4, 5, 6)(0))
    val element2= Option(Array())
    println(element1.headOption.mkString)
    println(s"Optional of :: ${element2.headOption.mkString}")
  }

}
