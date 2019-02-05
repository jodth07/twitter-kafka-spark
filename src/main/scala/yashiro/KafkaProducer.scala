package ichigo

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import twitter4j.Status

object KafkaProducer {

  val TOPIC = "colors"

  val props = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])

  val config = new ProducerConfig(props)

  val kafkaProducer = new KafkaProducer[String, String](props)

  def sendToKafka(data: Status) {
    val tweet_text = data.getText

    val msg = new ProducerRecord[String, String](TOPIC, tweet_text)
    kafkaProducer.send(msg)
  }

}
