package com.kotlin.orderapp

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
}