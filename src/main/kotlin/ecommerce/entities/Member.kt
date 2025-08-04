package ecommerce.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @Column(name = "name", nullable = false, length = 50)
    val name: String,
    @Column(name = "email", nullable = false, length = 100)
    val email: String,
    @Column(name = "password", nullable = false, length = 255)
    val password: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    val role: Role = Role.CUSTOMER,
    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    val cartItems: Set<CartItem> = emptySet(),
    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    val wishItems: Set<WishItem> = emptySet(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
) {
    enum class Role { CUSTOMER, ADMIN }
}
