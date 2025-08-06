package ecommerce.integration

import ecommerce.controller.admin.usecase.FindMembersWithRecentCartActivityUseCase
import ecommerce.controller.admin.usecase.FindTopProductsUseCase
import ecommerce.entities.MemberEntity
import ecommerce.entities.ProductEntity
import ecommerce.dto.CartItemRequestDTO
import ecommerce.repositories.CartItemRepository
import ecommerce.repositories.MemberRepository
import ecommerce.repositories.ProductRepository
import ecommerce.services.cart.CartItemServiceImpl
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class AdminServiceTest {
    @Autowired
    private lateinit var findTopProductsUseCase: FindTopProductsUseCase

    @Autowired
    private lateinit var findMembersWithRecentCartActivityUseCase: FindMembersWithRecentCartActivityUseCase

    @Autowired
    private lateinit var cartItemService: CartItemServiceImpl

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    private lateinit var member1: MemberEntity
    private lateinit var member2: MemberEntity
    private lateinit var product1: ProductEntity
    private lateinit var product2: ProductEntity

    @BeforeEach
    fun setup() {
        cartItemRepository.deleteAll()
        memberRepository.deleteAll()
        productRepository.deleteAll()

        member1 = memberRepository.save(MemberEntity(name = "m1", email = "m1@example.com", password = "pw"))!!
        member2 = memberRepository.save(MemberEntity(name = "m2", email = "m2@example.com", password = "pw"))!!

        product1 = productRepository.save(ProductEntity(name = "Mouse", price = 10.0, imageUrl = "mouse.jpg"))
        product2 = productRepository.save(ProductEntity(name = "Keyboard", price = 20.0, imageUrl = "keyboard.jpg"))

        cartItemService.addOrUpdate(CartItemRequestDTO(product1.id, 1), member1.id)
        cartItemService.addOrUpdate(CartItemRequestDTO(product2.id, 2), member1.id)
        cartItemService.addOrUpdate(CartItemRequestDTO(product2.id, 1), member2.id)
    }

    @Test
    fun `findTopProductsAddedInList30Days returns top products`() {
        val result = findTopProductsUseCase.findProducts()

        assertThat(result).isNotEmpty
        assertThat(result.map { it.name }).contains("Mouse", "Keyboard")
    }

    @Test
    fun `findMembersWithRecentCartActivity returns distinct members`() {
        val result = findMembersWithRecentCartActivityUseCase.findMembers()

        assertThat(result.map { it.email }).containsExactlyInAnyOrder("m1@example.com", "m2@example.com")
    }
}
