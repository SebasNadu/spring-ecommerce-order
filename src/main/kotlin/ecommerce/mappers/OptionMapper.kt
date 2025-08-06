package ecommerce.mappers

import ecommerce.entities.Option
import ecommerce.entities.Product
import ecommerce.model.OptionDTO

fun Option.toDTO(): OptionDTO = OptionDTO(name, quantity, product.id, id)

fun OptionDTO.toEntity(product: Product): Option = Option(name, quantity, product, id)
