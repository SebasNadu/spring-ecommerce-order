package ecommerce.mappers

import ecommerce.entities.CartItemEntity
import ecommerce.dto.CartItemResponseDTO

fun CartItemEntity.toDTO() = CartItemResponseDTO(id, member.id, product.toDTO(), quantity, addedAt)
