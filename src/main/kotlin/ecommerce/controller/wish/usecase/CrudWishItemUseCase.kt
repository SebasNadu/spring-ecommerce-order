package ecommerce.controller.wish.usecase

import ecommerce.model.WishItemRequestDTO
import ecommerce.model.WishItemResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CrudWishItemUseCase {
    fun save(
        wishItemRequestDTO: WishItemRequestDTO,
        memberId: Long,
    ): WishItemResponseDTO

    fun findByMember(memberId: Long): List<WishItemResponseDTO>

    fun findByMember(
        memberId: Long,
        page: Pageable,
    ): Page<WishItemResponseDTO>

    fun delete(
        wishItemRequestDTO: WishItemRequestDTO,
        memberId: Long,
    )

    fun deleteAll()
}
