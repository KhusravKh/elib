package de.attolabs.library.services.abstractions

import de.attolabs.library.dto.category.CategoryCreateDto
import de.attolabs.library.dto.category.CategoryDto
import de.attolabs.library.dto.category.CategoryUpdateDto

interface CategoryService {

    fun getAll(): List<CategoryDto>

    fun update(id: Long, categoryUpdateDto: CategoryUpdateDto)

    fun save(categoryCreateDto: CategoryCreateDto)
}