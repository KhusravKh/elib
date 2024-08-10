package de.attolabs.library.services.abstractions

import de.attolabs.library.dto.book.BookCreateDto
import de.attolabs.library.dto.book.BookDto
import de.attolabs.library.dto.book.BookUpdateDto
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface BookService {

    fun getAll(): List<BookDto>

    fun getAllPaginated(categoryId: Long, page: Int): Page<BookDto>

    fun save(bookCreateDto: BookCreateDto, image: MultipartFile)

    fun update(bookUpdateDto: BookUpdateDto, image: MultipartFile)
}