package de.attolabs.library.repositories

import de.attolabs.library.models.Book
import de.attolabs.library.repositories.apstractions.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class BookRepositoryImpl(entityManager: EntityManager) : BookRepository,
    BaseRepositoryImpl<Book>(entityManager, Book::class.java) {

    override fun getAll(): List<Book> {
        return entityManager.createQuery("SELECT b FROM Book b", Book::class.java).resultList
    }

    override fun getAllPaginated(categoryId: Long, pageRequest: PageRequest): Page<Book> {
        val books = entityManager.createQuery("SELECT b FROM Book b WHERE b.category.id = :categoryId", Book::class.java)
            .setParameter("categoryId", categoryId)
            .setFirstResult(pageRequest.pageNumber * pageRequest.pageSize)
            .setMaxResults(pageRequest.pageSize)
            .resultList

        val count = entityManager.createQuery("SELECT COUNT(b.id) FROM Book b WHERE b.category.id = :categoryId")
            .setParameter("categoryId", categoryId)
            .singleResult as Long
        return PageImpl(books, pageRequest, count)
    }

    override fun getAllPaginated(pageRequest: PageRequest): Page<Book> {
        val books = entityManager.createQuery("SELECT b FROM Book b", Book::class.java)
            .setFirstResult(pageRequest.pageNumber * pageRequest.pageSize)
            .setMaxResults(pageRequest.pageSize)
            .resultList

        val count = entityManager.createQuery("SELECT COUNT(b.id) FROM Book b")
            .singleResult as Long
        return PageImpl(books, pageRequest, count)
    }
}