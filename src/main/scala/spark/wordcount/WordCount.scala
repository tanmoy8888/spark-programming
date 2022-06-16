package spark.wordcount

import org.apache.spark.sql.SparkSession

/**
 * @author Tanmoy Mukherjee
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    val file = "src/main/resources/files/word.txt"
    val output = "target/output/count"
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("word-count-with-partition")
      .getOrCreate()
    val sc = spark.sparkContext
    val inputrdd = sc.textFile(file)
    val vowels = "AEIOU"
    val countrdd = inputrdd.flatMap(_.split(" "))
      .filter(w => vowels.contains(w.headOption.mkString.toUpperCase))
      .map((_, 1))
      .reduceByKey(_ + _)
    //.foreach(println)
    val partitionedrdd = countrdd.partitionBy(new WordPartitionar(5))
    partitionedrdd.saveAsTextFile(output)
  }


}
