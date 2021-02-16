package com.kotlin.orderapp

fun main(args: Array<String>) {
	val cartService: CartService = CartService()
	cartService.submitUserOrder(args);
}