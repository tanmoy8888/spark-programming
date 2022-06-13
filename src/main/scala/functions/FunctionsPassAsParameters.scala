package functions

/**
 * @author Tanmoy Mukherjee
 */
object FunctionsPassAsParameters {

  def add(i:Int) :Int = i
  def squere(i:Int):Int =i*i
  def cube(i:Int) :Int = i*i*i

  def myFunction(func:Int=>Int,lb:Int,ub:Int):Int= {
    var total =0
    for(element <- lb to ub){
      total+=func(element)
    }
    total
  }

  def main(args: Array[String]): Unit = {
    val func = myFunction(squere,1,3)
    println("func ------------->"+func)
  }
}
