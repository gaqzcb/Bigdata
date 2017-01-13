package main.scala.com.huawei.learn.core

import com.twitter.chill.KryoSerializer
import org.apache.spark.rdd.ShuffledRDD
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * Usage: GroupByTest [numMappers] [numKVPairs] [valSize] [numReducers]
  */
object GroupByTest {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf()
      .setAppName("GroupBy Test")
      .setMaster("local")
      .set("spark.logLineage", "true")
      .set("spark.network.timeout", "120000s")
      .set("spark.executor.heartbeatInterval", "120000s")
      /**
        * 取值为sort或者tungsten-sort，都是实例化SortShuffleManager
        */
      .set("spark.shuffle.manager", "tungsten-sort") // 这个参数貌似没什么用
      /**
        * 决定了ShuffleMapTask在写文件的时候使用哪种ShuffleWriter
        */
//      .set("spark.shuffle.sort.bypassMergeThreshold", "1")
      .set("spark.shuffle.spill.numElementsForceSpillThreshold", "1")

//      .set("spark.memory.offHeap.enabled", "true")
//      .set("spark.memory.offHeap.size", "1024")

      .set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")

    sparkConf.registerKryoClasses(Array())

    var numMappers = 4
    var numKVPairs = 100
    var valSize = 100
    var numReducers = 36

    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.parallelize(0 until numMappers, 1)
      .map(x => (x, x))
    val rdd2 = new ShuffledRDD[Int, Int, Int](rdd1, new HashPartitioner(2))
//    rdd2.setSerializer(new KryoSerializer(sparkConf))
    rdd2.setKeyOrdering(implicitly[Ordering[Int]])

    rdd2.collect.foreach(println)
//      .groupBy((x: Int) => x.toString, new HashPartitioner(2))
//      .collect
//      .foreach(println)



//    val pairs1 = sc.parallelize(0 until numMappers, numMappers).flatMap { p =>
//      val ranGen = new Random
//      var arr1 = new Array[(Int, Array[Byte])](numKVPairs)
//      for (i <- 0 until numKVPairs) {
//        val byteArr = new Array[Byte](valSize)
//        ranGen.nextBytes(byteArr)
//        arr1(i) = (ranGen.nextInt(Int.MaxValue), byteArr)
//      }
//      arr1
//    }//.cache
//
//    println(pairs1.toDebugString)
//
//    // Enforce that everything has been calculated and in cache
//    pairs1.count
////    println(pairs1.groupByKey(numReducers).count)

    sc.stop()
  }
}
