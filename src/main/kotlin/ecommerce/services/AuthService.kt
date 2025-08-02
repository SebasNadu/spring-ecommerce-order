package ecommerce.services

import ecommerce.exception.ForbiddenException
import ecommerce.infrastructure.JwtTokenProvider
import ecommerce.model.MemberDTO
import ecommerce.model.TokenRequestDTO
import ecommerce.model.TokenResponseDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(private val jwtTokenProvider: JwtTokenProvider, private val memberService: MemberService) {
    @Transactional
    fun login(tokenRequestDTO: TokenRequestDTO): TokenResponseDTO {
        if (checkInvalidLogin(tokenRequestDTO)) throw ForbiddenException("Invalid email or password.")
        val memberDTO = memberService.findByEmail(tokenRequestDTO.email)
        return createToken(memberDTO)
    }

    fun checkInvalidLogin(tokenRequestDTO: TokenRequestDTO): Boolean {
        val memberDTO = memberService.findByEmail(tokenRequestDTO.email)
        return tokenRequestDTO.email != memberDTO.email || tokenRequestDTO.password != memberDTO.password
    }

    @Transactional(readOnly = true)
    fun findMemberByToken(token: String): MemberDTO {
        val (email, _) = jwtTokenProvider.getPayload(token)
        return memberService.findByEmail(email)
    }

    @Transactional
    fun createToken(memberDTO: MemberDTO): TokenResponseDTO {
        val accessToken = jwtTokenProvider.createToken(memberDTO.email, memberDTO.role)
        return TokenResponseDTO(accessToken)
    }
}
