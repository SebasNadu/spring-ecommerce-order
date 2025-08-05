package ecommerce.controller.admin.usecase

import ecommerce.model.OptionDTO

interface CreateOptionUseCase {
    fun create(optionDTO: OptionDTO)
}
