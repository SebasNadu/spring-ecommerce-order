package ecommerce.services.stripe

import ecommerce.dto.PaymentRequest
import ecommerce.dto.StripeResponse
import ecommerce.infrastructure.StripeClient
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap

@Service
class StripeService(
    private val stripeClient: StripeClient
) {
    fun createIntent(paymentRequest: PaymentRequest): StripeResponse {
        val form = LinkedMultiValueMap<String, String>().apply {
            add("amount", paymentRequest.amount.toString())
            add("currency", paymentRequest.currency)
            add("payment_method", paymentRequest.paymentMethod)
            add("automatic_payment_methods[enabled]", "true")
        }
        return stripeClient.createPaymentIntent(form)
    }
}
