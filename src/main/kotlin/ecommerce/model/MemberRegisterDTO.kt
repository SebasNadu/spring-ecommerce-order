package ecommerce.model

import ecommerce.util.ValidationMessages.EMAIL_BLANK
import ecommerce.util.ValidationMessages.EMAIL_INVALID
import ecommerce.util.ValidationMessages.MEMBER_NAME_REQUIRED
import ecommerce.util.ValidationMessages.PASSWORD_BLANK
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class MemberRegisterDTO(
    @field:NotBlank(message = MEMBER_NAME_REQUIRED)
    var name: String,
    @field:NotBlank(message = EMAIL_BLANK)
    @field:Email(message = EMAIL_INVALID)
    var email: String,
    @field:NotBlank(message = PASSWORD_BLANK)
    var password: String,
)
