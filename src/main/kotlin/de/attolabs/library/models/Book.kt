package de.attolabs.library.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "books")
class Book(
    id: Long?,
    @Column(name = "title", nullable = false) var title: String,
    @Column(name = "authors", nullable = false) var authors: String,
    @Column(name = "description", nullable = false) var description: String,
    @Column(name = "path", nullable = false) var path: String,
    @Column(name = "image") var image: String?,
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false) @ManyToOne var category: Category
) : BaseEntity(id)