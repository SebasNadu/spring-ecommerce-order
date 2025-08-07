package ecommerce.integration

import ecommerce.dto.PaymentRequest
import ecommerce.services.stripe.StripeService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StripeServiceTest {
    @Autowired
    private lateinit var stripeService: StripeService

    @Test
    fun test1() {
        val actual =
            stripeService.createIntent(
                PaymentRequest(
                    amount = 1000,
                    currency = "USD",
                    paymentMethod = "pm_card_visa",
                ),
            )
        println(actual)
//        Assertions.assertNotNull(actual)
    }
}
