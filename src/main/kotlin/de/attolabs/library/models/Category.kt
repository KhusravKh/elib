package de.attolabs.library.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "categories")
class Category(id: Long?, @Column(name = "name", nullable = false, unique = true) var name: String) : BaseEntity(id)