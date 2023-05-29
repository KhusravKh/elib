package de.attolabs.library.repositories.apstractions

import de.attolabs.library.models.Category
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : BaseRepository<Category> {

    fun getAll(): List<Category>
}