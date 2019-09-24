package com.redislabs.provider.redis

import org.scalatest.{FunSuite, Matchers}
import redis.clients.jedis.util.JedisClusterCRC16

import java.net.URI

import org.apache.spark.SparkConf
import redis.clients.jedis.util.{JedisClusterCRC16, JedisURIHelper, SafeEncoder}
import redis.clients.jedis.{Jedis, Protocol}

class RedisTLSTest extends FunSuite with Matchers {

  val redisConfigTest = new RedisConfig(RedisEndpoint("127.0.0.1", 4159, "5x8eX3xH4dOndGahboQ5qNWu0fVMveY1", 0, 2000, true))
  val ConnectionPoolTest = ConnectionPool.connect(RedisEndpoint("127.0.0.1", 4159, "5x8eX3xH4dOndGahboQ5qNWu0fVMveY1", 0, 2000, true))


  test("redisConfigTest") {
    redisConfigTest.getAuth shouldBe "5x8eX3xH4dOndGahboQ5qNWu0fVMveY1"
    redisConfigTest.getDB shouldBe 0
    redisConfigTest.initialHost.ssl shouldBe true
  }

  test("ConnectionPoolTest") {
    ConnectionPoolTest
  }
//
//  test("getNodes") {
//    redisTLSConfig.getNodes(RedisEndpoint("127.0.0.1", 6379, "passwd")).length shouldBe 1
//  }
}
