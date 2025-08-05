package ecommerce.controller.member.usecase

import ecommerce.model.MemberDTO
import ecommerce.model.TokenRequestDTO
import ecommerce.model.TokenResponseDTO

interface AuthUseCase {
    fun login(tokenRequestDTO: TokenRequestDTO): TokenResponseDTO

    fun checkInvalidLogin(tokenRequestDTO: TokenRequestDTO): Boolean

    fun findMemberByToken(token: String): MemberDTO

    fun createToken(memberDTO: MemberDTO): TokenResponseDTO
}
