package de.attolabs.library.services

import de.attolabs.library.dto.book.BookCreateDto
import de.attolabs.library.dto.book.BookDto
import de.attolabs.library.dto.book.BookUpdateDto
import de.attolabs.library.mappers.BookMapper
import de.attolabs.library.models.Book
import de.attolabs.library.repositories.apstractions.BookRepository
import de.attolabs.library.repositories.apstractions.CategoryRepository
import de.attolabs.library.services.abstractions.BookService
import de.attolabs.library.services.abstractions.FileStorage
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.RuntimeException

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val categoryRepository: CategoryRepository,
    private val fileStorage: FileStorage,
    private val bookMapper: BookMapper
) : BookService {

    override fun getAll(): List<BookDto> {
        val books = bookRepository.getAll()
        return books.map { bookMapper.toBookDto(it) }
    }

    override fun getAllPaginated(categoryId: Long, page: Int): Page<BookDto> {

        val paginatedBooks = if (categoryId > 0) {
            bookRepository.getAllPaginated(categoryId, PageRequest.of(page, 10))
        } else {
            bookRepository.getAllPaginated(PageRequest.of(page, 10))
        }

        return paginatedBooks.map { bookMapper.toBookDto(it) }
    }

    override fun save(bookCreateDto: BookCreateDto, image: MultipartFile) {
        val image = fileStorage.store(image)
        val category = categoryRepository.getById(bookCreateDto.categoryId)
            ?: throw RuntimeException("Category with id: ${bookCreateDto.categoryId} not found")
        val book = bookMapper.toBook(bookCreateDto, category)
        book.image = image
        bookRepository.save(book)
    }

    override fun update(bookUpdateDto: BookUpdateDto, image: MultipartFile) {
        fileStorage.store(image)
        val category = categoryRepository.getById(bookUpdateDto.categoryId)
            ?: throw RuntimeException("Category with id: ${bookUpdateDto.categoryId} not found")
        val book = bookMapper.toBook(bookUpdateDto, category)
        bookRepository.update(book)
    }
}