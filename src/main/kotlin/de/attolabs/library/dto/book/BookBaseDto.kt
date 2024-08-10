package de.attolabs.library.dto.book

abstract class BookBaseDto(
    var title: String,
    var authors: String,
    var description: String,
    var categoryId: Long,
    var path: String
)