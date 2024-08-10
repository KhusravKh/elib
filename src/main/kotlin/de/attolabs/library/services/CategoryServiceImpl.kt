package de.attolabs.library.services

import de.attolabs.library.dto.category.CategoryCreateDto
import de.attolabs.library.dto.category.CategoryDto
import de.attolabs.library.dto.category.CategoryUpdateDto
import de.attolabs.library.mappers.CategoryMapper
import de.attolabs.library.repositories.apstractions.CategoryRepository
import de.attolabs.library.services.abstractions.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository, private val categoryMapper: CategoryMapper): CategoryService {

    override fun getAll(): List<CategoryDto> {
        val categories = categoryRepository.getAll()
        return categories.map { categoryMapper.toCategoryDto(it) }
    }

    override fun save(categoryCreateDto: CategoryCreateDto) {
        val category = categoryMapper.toCategory(categoryCreateDto)
        categoryRepository.save(category)
    }

    override fun update(id: Long, categoryUpdateDto: CategoryUpdateDto) {
        val category = categoryMapper.toCategory(categoryUpdateDto)
        category.id = id
        categoryRepository.update(category)
    }
}