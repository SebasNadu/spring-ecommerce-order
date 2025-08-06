package ecommerce.controller.member.usecase

import ecommerce.model.MemberDTO
import ecommerce.model.MemberRegisterDTO

interface CrudMemberUseCase {
    fun findAll(): List<MemberDTO>

    fun findById(id: Long): MemberDTO

    fun findByEmail(email: String): MemberDTO

    fun save(memberRegisterDTO: MemberRegisterDTO): MemberDTO

    fun deleteAll()

    fun validateEmailUniqueness(email: String)
}
