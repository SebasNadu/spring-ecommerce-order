package ecommerce.mappers

import ecommerce.dto.OptionDTO
import ecommerce.entities.OptionEntity
import ecommerce.entities.ProductEntity

fun OptionEntity.toDTO(): OptionDTO = OptionDTO(name, quantity, product.id, id)

fun OptionDTO.toEntity(product: ProductEntity): OptionEntity = OptionEntity(name, quantity, product, id)
