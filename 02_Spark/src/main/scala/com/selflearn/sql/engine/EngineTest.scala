package main.scala.com.huawei.learn.spark.sql.engine

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2017/1/5.
  *
  * http://blog.csdn.net/column/details/sparksql.html
  *
  */
object EngineTest extends App {
  System.setProperty("hadoop.home.dir", "D:\\Study\\code\\own\\DebugSpark\\home")
  val conf = new SparkConf()
    .setMaster("local")
    .set("spark.logLineage", "true")
    .set("spark.network.timeout", "120000s")
    .set("spark.executor.heartbeatInterval", "120000s")

  val spark = SparkSession.builder().config(conf).getOrCreate()

  val jsonData = spark.read.json("D:\\Study\\code\\own\\DebugSpark\\resource\\test_data\\json")

  jsonData.createOrReplaceTempView("table_1")
  import spark._
  val jsonData2 = sql("select * from table_1 where age > 1")

  jsonData2.collect()
    .foreach(println)
}
