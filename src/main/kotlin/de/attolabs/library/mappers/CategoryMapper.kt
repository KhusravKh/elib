package de.attolabs.library.mappers

import de.attolabs.library.dto.category.CategoryBaseDto
import de.attolabs.library.dto.category.CategoryDto
import de.attolabs.library.models.Category
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CategoryMapper {

    fun toCategory(categoryBaseDto: CategoryBaseDto): Category

    fun toCategoryDto(category: Category): CategoryDto
}