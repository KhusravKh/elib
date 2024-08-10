package de.attolabs.library.repositories.apstractions

import de.attolabs.library.models.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: BaseRepository<Book> {

    fun getAll(): List<Book>

    fun getAllPaginated(categoryId: Long, pageRequest: PageRequest): Page<Book>

    fun getAllPaginated(pageRequest: PageRequest): Page<Book>
}