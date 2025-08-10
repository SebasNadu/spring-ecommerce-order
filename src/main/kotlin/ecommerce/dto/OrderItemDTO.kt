package ecommerce.dto

import ecommerce.entities.OptionEntity

class OrderItemDTO(
    val optionId: Long,
    val quantity: Int,
    val unitPrice: Double,
    var id: Long? = null,
)
