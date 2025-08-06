package ecommerce.controller.cart

import ecommerce.annotation.LoginMember
import ecommerce.controller.cart.usecase.ManageCartItemUseCase
import ecommerce.model.CartItemRequestDTO
import ecommerce.model.CartItemResponseDTO
import ecommerce.model.MemberLoginDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cart")
class CartItemController(private val cartItemService: ManageCartItemUseCase) {
    @GetMapping
    fun getCartItemsWithProducts(
        @LoginMember member: MemberLoginDTO,
    ): ResponseEntity<List<CartItemResponseDTO>> {
        val cartItems = cartItemService.findByMember(member.id)
        return ResponseEntity.ok().body(cartItems)
    }

    @PostMapping
    fun addOrUpdateCartItem(
        @RequestBody cartItemRequestDTO: CartItemRequestDTO,
        @LoginMember member: MemberLoginDTO,
    ): ResponseEntity<CartItemResponseDTO> {
        val cartItemResponseDTO = cartItemService.addOrUpdate(cartItemRequestDTO, member.id)
        return ResponseEntity.ok().body(cartItemResponseDTO)
    }

    @DeleteMapping
    fun deleteCartItem(
        @RequestBody cartItemRequestDTO: CartItemRequestDTO,
        @LoginMember member: MemberLoginDTO,
    ): ResponseEntity<Unit> {
        cartItemService.delete(cartItemRequestDTO, member.id)
        return ResponseEntity.noContent().build()
    }
}
