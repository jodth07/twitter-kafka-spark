package ichigo

import org.apache.spark.SparkConf
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe


object SparkConsumer {

  def runSpark() {

    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("Kafka-Spark")

    val ssc = new StreamingContext(conf, Seconds(1))

    val TOPIC = Array("twitter")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "twitter-stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val kf_stream = KafkaUtils.createDirectStream[String, String](
      ssc, PreferConsistent, Subscribe[String, String](TOPIC, kafkaParams)
    )

    val stream_lowered = kf_stream.map(record => record.value().toString.toLowerCase)
    stream_lowered.print()

    ssc.start()
    ssc.awaitTermination()
  }

}