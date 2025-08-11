package ecommerce.mappers

import ecommerce.entities.ProductEntity
import ecommerce.dto.ProductRequestDTO
import ecommerce.dto.ProductResponseDTO

fun ProductEntity.toDTO(): ProductResponseDTO = ProductResponseDTO(id, name, price, imageUrl, options = options.map { it.toDTO() })

fun ProductRequestDTO.toEntity(): ProductEntity = ProductEntity(name, price, imageUrl, id = id)

fun ProductResponseDTO.toEntity(): ProductEntity = ProductEntity(name, price, imageUrl, id = id)
