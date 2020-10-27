package com.example.storage.schemas

import java.sql.Date

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

case class Event(timestamp: String,
                 visitorid: Int,
                 event: String,
                 itemid: Int,
                 transactionid: Int)

object Event {
  val SCHEMA = new StructType(Array(
    StructField("timestamp", StringType, false),
    StructField("visitorid", IntegerType, false),
    StructField("event", StringType, false),
    StructField("itemid", IntegerType, false),
    StructField("transactionid", IntegerType, true)
  ))
}