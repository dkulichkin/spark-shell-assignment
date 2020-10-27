package com.example

import org.apache.spark.sql.SparkSession
import com.example.storage.Storage


object PopulateEvents extends SparkJob {

  override def appName: String = "populate events"

  override def run(spark: SparkSession, config: UsageConfig, storage: Storage): Unit = {
    storage.writeToStorage(storage.events, s"events")
  }

}
