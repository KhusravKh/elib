package de.attolabs.library.repositories.apstractions

import de.attolabs.library.models.BaseEntity
import org.springframework.stereotype.Repository


@Repository
interface BaseRepository<TEntity> where TEntity: BaseEntity {
    fun getById(id: Long): TEntity?
    fun save(entity: TEntity): TEntity
    fun update(entity: TEntity): TEntity
    fun delete(entity: TEntity)
}