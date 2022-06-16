package spark.wordcount

import org.apache.spark.Partitioner

/**
 * @author Tanmoy Mukherjee
 */
class WordPartitionar(numberOfPartitions: Int) extends  Partitioner{

  override def numPartitions: Int = numberOfPartitions

  override def getPartition(key: Any): Int = {
    if(key.toString.headOption.mkString.equalsIgnoreCase("A")) return 0
    else if(key.toString.headOption.mkString.equalsIgnoreCase("E")) return 1
    else if(key.toString.headOption.mkString.equalsIgnoreCase("I")) return 2
    else if (key.toString.headOption.mkString.equalsIgnoreCase("O")) return 3
    else (key.toString.headOption.mkString.equalsIgnoreCase("U"))
    return 4
  }
}
