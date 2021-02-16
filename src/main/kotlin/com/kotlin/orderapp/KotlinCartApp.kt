package com.kotlin.orderapp

fun main(args: Array<String>) {
	val cartService: CartService = CartService()
	println("===========================Welcome to Cart Service App===========================")
	cartService.submitUserOrder(args);
	println("===========================Welcome to Cart Service App with Offer===========================")
	cartService.submitUserOrderWithSimpleOffer(args);
	println("===========================Thank you for shopping with us===========================")
}