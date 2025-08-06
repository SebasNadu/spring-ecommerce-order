package ecommerce.mappers

import ecommerce.entities.WishItemEntity
import ecommerce.dto.WishItemResponseDTO

fun WishItemEntity.toDTO() = WishItemResponseDTO(id, member.id, product.toDTO(), addedAt)
