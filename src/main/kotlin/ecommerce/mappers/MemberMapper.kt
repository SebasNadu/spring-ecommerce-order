package ecommerce.mappers

import ecommerce.entities.Member
import ecommerce.model.MemberDTO
import ecommerce.model.MemberLoginDTO
import ecommerce.model.MemberRegisterDTO

fun Member.toDTO() = MemberDTO(id, name, email, password, role)

fun MemberDTO.toLoginDTO() = MemberLoginDTO(id!!)

fun MemberDTO.toEntity() = Member(id, name, email, password, role)

fun MemberRegisterDTO.toEntity() = Member(null, name, email, password)
