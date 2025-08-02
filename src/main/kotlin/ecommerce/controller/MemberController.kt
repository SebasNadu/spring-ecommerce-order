package ecommerce.controller

import ecommerce.infrastructure.AuthorizationExtractor
import ecommerce.model.MemberDTO
import ecommerce.model.MemberRegisterDTO
import ecommerce.model.TokenRequestDTO
import ecommerce.model.TokenResponseDTO
import ecommerce.services.AuthService
import ecommerce.services.MemberService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val authService: AuthService,
    private val authorizationExtractor: AuthorizationExtractor,
    private val memberService: MemberService,
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody memberRegisterDTO: MemberRegisterDTO,
    ): ResponseEntity<TokenResponseDTO> {
        val memberDTO: MemberDTO = memberService.save(memberRegisterDTO)
        val tokenResponse = authService.createToken(memberDTO)
        return ResponseEntity.ok().body(tokenResponse)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody tokenRequestDTO: TokenRequestDTO,
    ): ResponseEntity<TokenResponseDTO> {
        val tokenResponse = authService.login(tokenRequestDTO)
        return ResponseEntity.ok().body(tokenResponse)
    }

    @GetMapping("/me/token")
    fun findMyInfo(request: HttpServletRequest): ResponseEntity<MemberDTO> {
        val token = authorizationExtractor.extractToken(request)
        val member = authService.findMemberByToken(token)
        return ResponseEntity.ok().body(member)
    }
}
