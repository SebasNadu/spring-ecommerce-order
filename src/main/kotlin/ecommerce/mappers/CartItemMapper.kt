package ecommerce.mappers

import ecommerce.entities.CartItem
import ecommerce.model.CartItemResponseDTO

fun CartItem.toDTO() = CartItemResponseDTO(id!!, member.id!!, product.toDTO(), quantity, addedAt)
