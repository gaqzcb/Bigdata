package com.selflearn.core.memory

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/12/28.
  */
object SortShuffleWriterTest extends App {
  val sparkConf = new SparkConf()
    .setAppName("Enable OffHeap Test")
    .setMaster("local")
    .set("spark.logLineage", "true")
    .set("spark.network.timeout", "120000s")
    .set("spark.executor.heartbeatInterval", "120000s")

    .set("spark.memory.offHeap.size", "1048576")

  val numMappers = 4
  val sc = new SparkContext(sparkConf)
  val rdd1 = sc.parallelize(0 until numMappers, 1)
//  rdd1.persist(StorageLevel.OFF_HEAP)
  rdd1.persist(StorageLevel.MEMORY_ONLY)

  rdd1.collect()

  sc.stop()
}
