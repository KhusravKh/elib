package de.attolabs.library.dto.book

class BookDto(var id: Long?, title: String, authors: String, description: String, categoryId: Long, path: String, var image: String) :
    BookBaseDto(title, authors, description, categoryId, path)