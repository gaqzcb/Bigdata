package main.scala.com.huawei.learn.spark.sql.datasource

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2017/1/5.
  */
object JsonTest extends App {
  System.setProperty("hadoop.home.dir", "D:\\Study\\code\\own\\DebugSpark\\home")
  val conf = new SparkConf()
    .setMaster("local")
  val spark = SparkSession.builder().config(conf).getOrCreate()

  val jsonData = spark.read.json("D:\\Study\\code\\own\\DebugSpark\\resource\\test_data\\json")
}
