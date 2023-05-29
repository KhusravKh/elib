package de.attolabs.library.repositories

import de.attolabs.library.models.BaseEntity
import de.attolabs.library.repositories.apstractions.BaseRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Transactional
@Repository
abstract class BaseRepositoryImpl<TEntity>(val entityManager: EntityManager, val tClass: Class<TEntity>) :
    BaseRepository<TEntity> where TEntity : BaseEntity {

    @Transactional(readOnly = true)
    override fun getById(id: Long): TEntity? {
        return entityManager.find(tClass, id)
    }

    override fun delete(entity: TEntity) {
        entityManager.remove(entity)
    }

    override fun update(entity: TEntity): TEntity {
        return entityManager.merge(entity)
    }

    override fun save(entity: TEntity): TEntity {
        entityManager.persist(entity)
        entityManager.flush()
        return entity
    }
}