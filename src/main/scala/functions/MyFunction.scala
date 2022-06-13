package functions

/**
 * @author Tanmoy Mukherjee
 */
object MyFunction {
  def main(args: Array[String]): Unit = {
   println(sum(1,4))
  }

  def sum(start: Int, end: Int): Int = {
    var total: Int = 0
    for (element <- start to end) {
       total += element
    }
    total
  }

}
