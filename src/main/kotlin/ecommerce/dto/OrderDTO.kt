package ecommerce.dto

import ecommerce.entities.OrderEntity
import ecommerce.model.Member
import java.time.LocalDateTime

class OrderDTO(
    val status: OrderEntity.OrderStatus,
    val totalAmount: Double,
    val memberId: Long,
    val items: List<OrderItemDTO> = mutableListOf(),
    val payments: List<PaymentDTO> = mutableListOf(),
    val createdAt: String,
    val updatedAt: String,
    var id: Long? = null,
)
