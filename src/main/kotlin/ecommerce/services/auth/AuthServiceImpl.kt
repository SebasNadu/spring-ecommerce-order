package ecommerce.services.auth

import ecommerce.controller.member.usecase.AuthUseCase
import ecommerce.controller.member.usecase.CrudMemberUseCase
import ecommerce.exception.ForbiddenException
import ecommerce.infrastructure.JwtTokenProvider
import ecommerce.model.MemberDTO
import ecommerce.model.TokenRequestDTO
import ecommerce.model.TokenResponseDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(private val jwtTokenProvider: JwtTokenProvider, private val memberService: CrudMemberUseCase) :
    AuthUseCase {
    @Transactional
    override fun login(tokenRequestDTO: TokenRequestDTO): TokenResponseDTO {
        if (checkInvalidLogin(tokenRequestDTO)) throw ForbiddenException("Invalid email or password.")
        val memberDTO = memberService.findByEmail(tokenRequestDTO.email)
        return createToken(memberDTO)
    }

    override fun checkInvalidLogin(tokenRequestDTO: TokenRequestDTO): Boolean {
        val memberDTO = memberService.findByEmail(tokenRequestDTO.email)
        return tokenRequestDTO.email != memberDTO.email || tokenRequestDTO.password != memberDTO.password
    }

    @Transactional(readOnly = true)
    override fun findMemberByToken(token: String): MemberDTO {
        val (email, _) = jwtTokenProvider.getPayload(token)
        return memberService.findByEmail(email)
    }

    @Transactional
    override fun createToken(memberDTO: MemberDTO): TokenResponseDTO {
        val accessToken = jwtTokenProvider.createToken(memberDTO.email, memberDTO.role)
        return TokenResponseDTO(accessToken)
    }
}
