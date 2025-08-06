package ecommerce.entities

import ecommerce.exception.InsufficientStockException
import ecommerce.exception.InvalidOptionQuantityException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OptionTest {
    val product = Product(name = "Test Product", price = 100.0, imageUrl = "https//::test.jpg")

    @Test
    fun `subtract should reduce quantity correctly`() {
        val option =
            Option(
                name = "Test",
                quantity = 10,
                product = product,
            )
        option.subtract(3)
        assertEquals(7, option.quantity)
    }

    @Test
    fun `subtract should throw if quantity is less than 1`() {
        val option = Option(name = "Test", quantity = 10, product)
        assertThrows<InvalidOptionQuantityException> {
            option.subtract(0)
        }
    }

    @Test
    fun `subtract should throw if subtracting more than stock`() {
        val option = Option(name = "Test", quantity = 5, product)
        assertThrows<InsufficientStockException> {
            option.subtract(10)
        }
    }
}
