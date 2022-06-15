package spark.util

import org.apache.commons.lang.StringUtils
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkContext
import spark.rdd.FirstRdd.fs

/**
 * @author Tanmoy Mukherjee
 */
class AppUtil {
  val prefix = "backup-"
  val postfix = "-1"
  def checkFileExistance(path:String,sc:SparkContext): Boolean ={
    var exists = false;
    val p = new Path(path)
    if(fs.exists(p)){
      println("File exists in the location")
      exists = true
    } else{
      println("File does not exists in the location")
      exists = false
    }
   exists
  }

  /**
   * This below method will check whether this is a file or directory
   * @param path
   * @param fs
   */
  def renameOutput(path:String, fs:FileSystem): Unit ={
    if(fs.isDirectory(new Path(path))) {
      println("This is a output directory")
      val map = getPathandFileOrDirectoryName(path)
      if(map.contains("foldername") && map.contains("path")){
        val foldername = map.get("foldername")
        val pathname = map.get("path")
        replacePreviousOutput(fs,pathname.mkString,foldername.mkString)
      }
    }
    else if(fs.isFile(new Path(path))){
      println("This is a file")
      val map = getPathandFileOrDirectoryName(path)
    }
  }

  def replacePreviousOutput(fs:FileSystem,path:String,oldfoldername:String): Unit ={
    if(oldfoldername.contains("-")){
      val namearr = oldfoldername.split("-")
      val lastnumber = namearr.lastOption.mkString.toInt
      println(s"Existing last number $lastnumber")
      val modifiedlastnumber = lastnumber+1
      val source = path+oldfoldername
      val destination = path+modifiedlastnumber
      fs.rename(new Path(source),new Path(destination))
    }
    else{
      val newfoldername=oldfoldername+"-1"
    }
  }
  def getPathandFileOrDirectoryName(path:String): collection.mutable.Map[String,String] ={
    var map = collection.mutable.Map[String,String]()
    val patharr = path.split("/")
    // Get the last element (Folder/File name)
    val lastelement = patharr.lastOption
    var filename =""
    if(!lastelement.isEmpty){
      if(lastelement.mkString.contains(".")){
        println("This is a file")
        filename = lastelement.mkString
        map+=("filename" -> filename)
      }
      else{
        println("This is a directory")
        filename = lastelement.mkString
        map+=("foldername" -> filename)
      }
      // get the path , apart from last element(Folder/File name)
      if(!lastelement.isEmpty){
        val path:String = StringUtils.remove(path,lastelement.mkString)
        map+=("path" -> path)
      }
    }
    println("Printing Map")
    map.foreach(println)
    map
  }

  def renameExistingFile(path:String, fs:FileSystem): Unit ={
    println("inside renameExistingFile().....")
    println(s"path------------>$path")
    if(path.contains("-")) {
      val filearr = path.split("/")
      val filename = filearr.last
      if (filename.contains("-")) {
        println("inside filename.contains(\"-\")).....")
        val digit = filename.split("-").last.toInt
        val newpostfix = (digit + 1).toString
        println(s"newpostfix--------->$newpostfix")
        var filepath = ""
        if (filearr.length > 1) {
          for (element <- 0 to filearr.length - 1) {
            filepath += element
          }
        }
        filepath = filepath + newpostfix
        val destpath = renameTheFile(path, fs, filepath)
        println(s"destpath-------->$destpath")
        fs.rename(new Path(path), new Path(destpath))
      }
    }
    else{
      println("Inside else part")
      val destpath = renameTheFile(path,fs,postfix)
      println(s"destpath----------------->$destpath")
      fs.rename(new Path(path),new Path(destpath))
    }
  }

  def renameTheFile(path:String,fs:FileSystem,post:String): String ={
    val filearr = path.split("/")
    val length = filearr.length
    val filename = filearr.last
    println(s"filename--------->$filename")
    val rename = prefix+filename+post
    var filepath =""
    if(length>1){
      val l = length-1
      var i=1
      for(e <- filearr){
        if(l<i){
          filepath +=e
        }
      }
    }
    println(s"filepath---before-------->$filepath")
    filepath= filepath+post
    println(s"filepath----postfix-------->$filepath")
    filepath
  }

}
