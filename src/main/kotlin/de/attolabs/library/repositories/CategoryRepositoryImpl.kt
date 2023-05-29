package de.attolabs.library.repositories

import de.attolabs.library.models.Category
import de.attolabs.library.repositories.apstractions.CategoryRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class CategoryRepositoryImpl(entityManager: EntityManager) : CategoryRepository,
    BaseRepositoryImpl<Category>(entityManager, Category::class.java) {

    override fun getAll(): List<Category> {
        return entityManager.createQuery("SELECT c FROM Category c", Category::class.java).resultList
    }
}