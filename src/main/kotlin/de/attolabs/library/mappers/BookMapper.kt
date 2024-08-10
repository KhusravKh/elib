package de.attolabs.library.mappers

import de.attolabs.library.dto.book.BookBaseDto
import de.attolabs.library.dto.book.BookDto
import de.attolabs.library.models.Book
import de.attolabs.library.models.Category
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface BookMapper {

    @Mapping(target = "category", source = "category")
    @Mapping(target = "id", expression = "java(null)")
    fun toBook(bookBaseDto: BookBaseDto, category: Category): Book

    @Mapping(target = "categoryId", source = "category.id")
    fun toBookDto(book: Book): BookDto

}