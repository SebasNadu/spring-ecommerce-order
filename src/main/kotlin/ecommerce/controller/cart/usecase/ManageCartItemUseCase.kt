package ecommerce.controller.cart.usecase

import ecommerce.model.CartItemRequestDTO
import ecommerce.model.CartItemResponseDTO

interface ManageCartItemUseCase {
    fun addOrUpdate(
        cartItemRequestDTO: CartItemRequestDTO,
        memberId: Long,
    ): CartItemResponseDTO

    fun findByMember(memberId: Long): List<CartItemResponseDTO>

    fun delete(
        cartItemRequestDTO: CartItemRequestDTO,
        memberId: Long,
    )

    fun deleteAll()
}
