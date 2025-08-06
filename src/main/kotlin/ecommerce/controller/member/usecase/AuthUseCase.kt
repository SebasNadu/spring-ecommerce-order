package ecommerce.controller.member.usecase

import ecommerce.model.Member
import ecommerce.dto.TokenRequestDTO
import ecommerce.dto.TokenResponseDTO

interface AuthUseCase {
    fun login(tokenRequestDTO: TokenRequestDTO): TokenResponseDTO

    fun checkInvalidLogin(tokenRequestDTO: TokenRequestDTO): Boolean

    fun findMemberByToken(token: String): Member

    fun createToken(memberDTO: Member): TokenResponseDTO
}
