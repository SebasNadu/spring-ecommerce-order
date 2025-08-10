package ecommerce.integration

import ecommerce.exception.PaymentFailedException
import ecommerce.model.PaymentRequest
import ecommerce.services.stripe.StripeService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StripeServiceTest {
    @Autowired
    private lateinit var stripeService: StripeService

    @Test
    fun `createPaymentIntent returns valid response from Stripe`() {
        val paymentRequest =
            PaymentRequest(
                amount = 1000.0,
                currency = "usd",
                paymentMethod = "pm_card_visa",
            )

        val response = stripeService.createPaymentIntent(paymentRequest)

        println(response)

        assertNotNull(response)
        assertEquals(1000.0, response.amountDecimal)
        assertEquals("usd", response.currency?.lowercase())
        assertTrue(
            response.status.equals("requires_payment_method", ignoreCase = true) ||
                response.status.equals("succeeded", ignoreCase = true) ||
                response.status.equals("requires_action", ignoreCase = true),
        )
    }

    @Test
    fun `createPaymentIntent fails with radar block payment method`() {
        val paymentRequest =
            PaymentRequest(
                amount = 1000.0,
                currency = "usd",
                paymentMethod = "pm_card_radarBlock",
            )

        val exception =
            assertThrows<PaymentFailedException> {
                stripeService.createPaymentIntent(paymentRequest)
            }
        println("Expected failure: ${exception.message}")
        assertTrue(exception.message!!.contains("Stripe payment initialization failed"))
    }

    @Test
    fun `createPaymentIntent fails for charge declined`() {
        val paymentRequest =
            PaymentRequest(
                amount = 1000.0,
                currency = "usd",
                paymentMethod = "pm_card_visa_chargeDeclined",
            )

        val exception =
            assertThrows<PaymentFailedException> {
                stripeService.createPaymentIntent(paymentRequest)
            }
        println(">>>>Expected failure: ${exception.message}")
        assertTrue(exception.message!!.contains("Stripe payment initialization failed"))
    }
}
