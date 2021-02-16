import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import com.kotlin.orderapp.CartService
import org.assertj.core.api.Assertions.assertThat

class CartServiceSpec {

	val service: CartService = CartService()

	@Test
	@DisplayName("test calculate the correct amount for given inputs")
	fun testCalculateAmountForGivenInputs() {
		val cartService = CartService()
		val inputValues = arrayOf<String>("Apple", "Orange", "Apple")
		var totalAmount = cartService.submitUserOrder(inputValues)
		assertThat(totalAmount).isEqualTo(1.55)
	}

	@Test
	@DisplayName("test calculate the correct amount for given inputs with SimpleOffer")
	fun testCalculateAmountForGivenInputsWithSimpleOffer() {
		val cartService = CartService()
		val inputValues = arrayOf<String>("Apple", "Orange", "Apple")
		var totalAmount = cartService.submitUserOrderWithSimpleOffer(inputValues)
		assertThat(totalAmount).isEqualTo(0.8)
	}
}