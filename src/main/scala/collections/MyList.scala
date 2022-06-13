package collections

/**
 * @author Tanmoy Mukherjee
 */
object MyList {
  def main(args: Array[String]): Unit = {
    val sum = (1 to 100).toList
                             .filter(_%2 ==0)
                             .reduce(_ + _)

    println(s"Sum of all even numbers are $sum")
  }

}
