package com.example.storage

import java.util.Properties

import org.apache.spark.sql.{Dataset, SparkSession}

class PostgresStorage(spark: SparkSession) extends Storage(spark: SparkSession) {

  val connectionProperties = new Properties()
  val jdbcDevHostname = "localhost"
  val jdbcDevPort     = 5432
  val jdbcDevDatabase = "postgres"
  val jdbcDevUrl      = s"jdbc:postgresql://${jdbcDevHostname}:${jdbcDevPort}/${jdbcDevDatabase}"

  connectionProperties.setProperty("Driver", "org.postgresql.Driver")
  connectionProperties.put("user", "postgres")
  connectionProperties.put("password", "example")

  override def writeToStorage(ds: Dataset[_], location: String): Unit = {
    ds.write.jdbc(jdbcDevUrl, location, connectionProperties)
  }

  override def readFromStorage(location: String): Dataset[_] = {
    spark.read.jdbc(jdbcDevUrl, location, connectionProperties)
  }

}
