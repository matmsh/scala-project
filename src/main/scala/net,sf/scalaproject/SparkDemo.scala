package net

import org.apache.spark.{SparkContext, SparkConf}



/**
 * Created by matmsh on 02/05/15.
 */
object SparkDemo {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("demo").setMaster("local")

    val sparkContext = new SparkContext(sparkConf)

    val rdd = sparkContext.parallelize(List(1,2,3))

    rdd.map (_ *100).foreach(println)

  }

}
