package main.scala.com.selflearn.sql.optimizer

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2017/1/5.
  *
  * http://blog.csdn.net/column/details/sparksql.html
  *
  */
object EliminateOuterJoinTest extends App {
  System.setProperty("hadoop.home.dir", "D:\\Study\\code\\own\\DebugSpark\\home")
  val conf = new SparkConf()
    .setMaster("local")
    .set("spark.logLineage", "true")
    .set("spark.network.timeout", "120000s")
    .set("spark.executor.heartbeatInterval", "120000s")

  val spark = SparkSession.builder().config(conf).getOrCreate()

  val jsonData = spark.read.json("D:\\Study\\code\\own\\DebugSpark\\resource\\test_data\\json")

  jsonData.createOrReplaceTempView("t1")
  import spark._
  val jsonData2 = sql(
    """
      |SELECT * FROM t1 a LEFT JOIN t1 b ON a.age=b.age WHERE a.age>1
    """.stripMargin)

  jsonData2.show()
}
