package ecommerce.mappers

import ecommerce.dto.CartItemResponseDTO
import ecommerce.entities.CartItemEntity

fun CartItemEntity.toDTO() = CartItemResponseDTO(id, member.id, product.toDTO(), quantity, addedAt)
