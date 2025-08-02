package ecommerce.services

import ecommerce.model.MemberDTO
import ecommerce.model.MemberRegisterDTO

interface MemberService {
    fun findAll(): List<MemberDTO>

    fun findById(id: Long): MemberDTO

    fun findByEmail(email: String): MemberDTO

    fun save(memberRegisterDTO: MemberRegisterDTO): MemberDTO

    fun validateEmailUniqueness(email: String)

    fun deleteAll()
}
