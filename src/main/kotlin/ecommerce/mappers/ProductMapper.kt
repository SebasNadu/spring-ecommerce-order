package ecommerce.mappers

import ecommerce.entities.Product
import ecommerce.model.ProductRequestDTO
import ecommerce.model.ProductResponseDTO

fun Product.toDTO(): ProductResponseDTO = ProductResponseDTO(id, name, price, imageUrl, options = options.map { it.toDTO() })

fun ProductRequestDTO.toEntity(): Product = Product(name, price, imageUrl, id = id)

fun ProductResponseDTO.toEntity(): Product = Product(name, price, imageUrl, id = id)
