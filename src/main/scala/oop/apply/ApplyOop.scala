package oop.apply

/**
 * @author Tanmoy Mukherjee
 */
class ApplyOop(var name:String,var title:String,var id:Int,var age:Int) {
  def show() = println(s"Name , $name , title $title , id $id , age $age ")
}
object ApplyOop{
  def apply(name:String,title:String,id:Int,age:Int): ApplyOop ={
    new ApplyOop(name,title,id, age)
  }
  def apply(name:String,title:String,id:String,age:Int): ApplyOop ={
    new ApplyOop(name,title,id.toInt, age)
  }

  def main(args: Array[String]): Unit = {
    val normal = ApplyOop("Tanmoy","Mukherjee",1234,24)
    val overloaded = ApplyOop("Tanmoy","Mukherjee","1234",24)
    normal.show
    overloaded.show
  }
}
