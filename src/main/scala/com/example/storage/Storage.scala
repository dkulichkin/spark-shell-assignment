package com.example.storage

import com.example.storage.schemas._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import org.apache.spark.sql.functions.from_unixtime

abstract class Storage(spark: SparkSession) {
  import spark.implicits._

  def writeToStorage(ds: Dataset[_], location: String)

  def readFromStorage(location: String): Dataset[_]

  def events: Dataset[Event] = readCsv[Event]("/Users/dmitriy/Downloads/archive/events.csv", Event.SCHEMA)

  private def readCsv[T: Encoder](location: String, schema: StructType): Dataset[T] = {
    spark.read
      .format("csv")
      .option("header", "true")
      .schema(schema)
      .load(location)
      //.withColumn("timestamp", from_unixtime($"timestamp"))
      .as[T]
  }
}
