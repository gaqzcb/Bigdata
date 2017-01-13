package com.selflearn.core.shuffle

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

// Just use SortShuffleWriter for Shuffle-Write-Phase

// not registered into KryoSerializer, so shuffle phase wouldn't select UnsafeShuffleWriter
case class MyString(s: String)

/**
  * Created by Administrator on 2016/12/28.
  */
object SortShuffleWriterTest extends App {
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
  sc.parallelize(0 until numMappers, 1)
      .map(x => (x, MyString(x.toString)))
      .groupByKey(new HashPartitioner(2))
      .collect
      .foreach(println)

  sc.stop()
}
