package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object SparkExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("SparkExample").getOrCreate()
    val driver = "org.postgresql.Driver"
    val url = s"jdbc:postgresql://localhost:5432/postgres"

    val eventSchema = new StructType(Array(
      StructField("timestamp", StringType, false),
      StructField("visitorid", IntegerType, false),
      StructField("event", StringType, false),
      StructField("itemid", IntegerType, false),
      StructField("transactionid", IntegerType, true)
    ))

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .schema(eventSchema)
      .load("/Users/dmitriy/Downloads/archive/events.csv")

    df.take(10).foreach(println)
    df.printSchema()
    print(df.count())

    val pgDF = spark.read
      .format("jdbc")
      .option("driver", driver)
      .option("url", url)
      .option("dbtable", "events")
      .option("user", "postgres").option("password","example").load()

    pgDF.show(20)


    spark.stop()
  }
}
