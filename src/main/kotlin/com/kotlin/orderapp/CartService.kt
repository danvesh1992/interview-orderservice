package com.kotlin.orderapp

import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.KafkaProducer
import java.util.Properties
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

class CartService {

	fun submitUserOrder(args: Array<String>): Double {
		var amount: Double = 0.0
		for (arg: String in args) {
			println("Selected input is: " + arg)
			when (arg) {
				"Apple" -> amount = amount + 65
				"Orange" -> amount = amount + 25
				else -> {
					println("Given input type is not correct")
				}
			}
		}
		println("total amount for the selected inputs : " + (amount / 100))
		return (amount / 100)
	}


	fun submitUserOrderWithSimpleOffer(args: Array<String>): Double {
		val fruitsCount = HashMap<String, Int>()
		fruitsCount["Apple"] = 0
		fruitsCount["Orange"] = 0

		for (arg: String in args) {
			println("Selected Fruit is: " + arg)

			when (arg) {
				"Apple" -> fruitsCount.set("Apple", fruitsCount.get("Apple")!! + 1)
				"Orange" -> fruitsCount.set("Orange", fruitsCount.get("Orange")!! + 1)
				else -> {
					println("Given input type is not found or correct")
				}
			}
		}

		println("Selected Fruits: " + fruitsCount)
		var amount: Double = 0.0

		for ((key, value) in fruitsCount) {
			println(key + " " + value)
			when (key) {
				"Apple" -> amount = amount + ((value / 2) * 65) + (value % 2 * 65)
				"Orange" -> amount = amount + ((value / 3) * 2 * 15) + (value % 3 * 15)
				else -> {
					println("Invalid Item Found")
				}
			}
		}
		println("total amount for the selected inputs with Offer is : " + (amount / 100))

		return (amount / 100)
	}

	fun publishOrderEvent() {
		val kafkaproducer: Producer<String, String> = createKafkaOrderProducer("localhost:9092")
		kafkaproducer.send(ProducerRecord("cart-order-submit", "Order is submitted!!")).get()
	}

	fun createKafkaOrderProducer(brokers: String): Producer<String, String> {
		val properties = Properties()
		properties["bootstrap.servers"] = brokers
		properties["key.serializer"] = StringSerializer::class.java.canonicalName
		properties["value.serializer"] = StringSerializer::class.java.canonicalName
		return KafkaProducer<String, String>(properties)
	}

	fun consumeOrderEvent() {
		val kafkaConsumer: Consumer<String, String> = createKafkaOrderConsumer("localhost:9092")
		kafkaConsumer.subscribe(listOf("cart-order-submit"));
		while (true) {
			val cartOrderRecords = kafkaConsumer.poll(Duration.ofSeconds(1))
			println(cartOrderRecords);
		}

	}


	fun createKafkaOrderConsumer(brokers: String): Consumer<String, String> {
		val properties = Properties()
		properties["bootstrap.servers"] = brokers
		properties["group.id"] = "person-processor"
		properties["key.deserializer"] = StringDeserializer::class.java
		properties["value.deserializer"] = StringDeserializer::class.java
		return KafkaConsumer<String, String>(properties)
	}
}