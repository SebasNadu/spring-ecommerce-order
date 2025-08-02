package ecommerce.services

import ecommerce.model.WishItemRequestDTO
import ecommerce.model.WishItemResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WishItemService {
    fun save(
        wishItemRequestDTO: WishItemRequestDTO,
        memberId: Long,
    ): WishItemResponseDTO

    fun findByMember(memberId: Long): List<WishItemResponseDTO>

    fun findByMember(memberId: Long, page: Pageable): Page<WishItemResponseDTO>

    fun delete(wishItemRequestDTO: WishItemRequestDTO, memberId: Long)

    fun deleteAll()
}