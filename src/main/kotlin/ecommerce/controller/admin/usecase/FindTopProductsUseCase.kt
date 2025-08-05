package ecommerce.controller.admin.usecase

import ecommerce.model.TopProductDTO

interface FindTopProductsUseCase {
    fun findProducts(): List<TopProductDTO>
}
