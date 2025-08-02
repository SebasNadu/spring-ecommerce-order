package ecommerce.mappers

import ecommerce.entities.Member
import ecommerce.model.MemberDTO
import ecommerce.model.MemberLoginDTO

fun Member.toDTO() = MemberDTO(id, name, email, password, role)

fun MemberDTO.toLoginDTO() = MemberLoginDTO(id!!)

fun MemberDTO.toEntity() = Member(id, name, email, password, role)
