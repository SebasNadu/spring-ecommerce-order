package ecommerce.mappers

import ecommerce.entities.WishItem
import ecommerce.model.WishItemResponseDTO

fun WishItem.toDTO() = WishItemResponseDTO(id!!, member.id!!, product.toDTO(), addedAt)
