package main.scala.com.huawei.learn.core.shuffle

import org.apache.spark.rdd.ShuffledRDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/12/28.
  */
object UnsafeShuffleWriterTest extends App {
  val sparkConf = new SparkConf()
    .setAppName("GroupBy Test")
    .setMaster("local")
    .set("spark.logLineage", "true")
    .set("spark.network.timeout", "120000s")
    .set("spark.executor.heartbeatInterval", "120000s")

    // bypass BypassMergeSortShuffleWriter
    .set("spark.shuffle.sort.bypassMergeThreshold", "0")

  val numMappers = 40000000
  val sc = new SparkContext(sparkConf)
  val rdd1 = sc.parallelize(0 until numMappers, 1).map(x => x -> x.toString)
  val rdd2 = new ShuffledRDD[Int, String, String](rdd1, new HashPartitioner(2))
      .collect
      .foreach(println)

  sc.stop()
}
