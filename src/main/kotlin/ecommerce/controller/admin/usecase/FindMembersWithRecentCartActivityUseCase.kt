package ecommerce.controller.admin.usecase

import ecommerce.model.ActiveMemberDTO

interface FindMembersWithRecentCartActivityUseCase {
    fun findMembers(): List<ActiveMemberDTO>
}
