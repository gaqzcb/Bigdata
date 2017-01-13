package main.scala.com.huawei.learn.scala

/**
  * Created by Administrator on 2017/1/7.
  */
object ProductTest extends App {

  case class X(a: String, b: Int) extends Product {
    val c: String = "4545"
  }

  val x = X("zcb", 10)
  println(x.productArity)
  println(x.productElement(0))
  println(x.productElement(1))
}
