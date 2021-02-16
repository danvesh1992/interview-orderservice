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
}