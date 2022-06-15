package spark.rdd

import org.apache.hadoop.fs.FileSystem
import org.apache.spark.{SparkConf, SparkContext}
import spark.util.AppUtil

import java.net.URI
import java.util.logging.Logger

/**
 * @author Tanmoy Mukherjee
 */

object FirstRdd {

  @transient
  lazy val log = Logger.getLogger(getClass.getName)
  val file ="target/firstrdd/data"
  val conf = new SparkConf().setAppName("First RDD")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  val configuration = sc.hadoopConfiguration
  val fs = FileSystem.get(URI.create(file),configuration)
  val a = new AppUtil

  def main(args: Array[String]): Unit = {

    val arr =Array(1,2,3,4)
    val  rdd = sc.parallelize(arr)
    val exists = a.checkFileExistance(file,sc)
    if(exists){
      println("output exists....")
      a.renameOutput(file,fs)
    }
    rdd.saveAsTextFile(file)
    log.info("RDD writes successfully")
    println("RDD Writes........")
    sc.stop
  }
}
