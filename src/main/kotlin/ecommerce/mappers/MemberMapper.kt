package ecommerce.mappers

import ecommerce.entities.Member
import ecommerce.model.MemberDTO
import ecommerce.model.MemberLoginDTO
import ecommerce.model.MemberRegisterDTO

fun Member.toDTO() = MemberDTO(name, email, password, role, id)

fun MemberDTO.toLoginDTO() = MemberLoginDTO(id)

fun MemberDTO.toEntity() = Member(name, email, password, role, id = id!!)

fun MemberRegisterDTO.toEntity() = Member(name, email, password)
