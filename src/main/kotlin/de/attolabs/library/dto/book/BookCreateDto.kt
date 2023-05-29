package de.attolabs.library.dto.book

import org.springframework.web.multipart.MultipartFile

class BookCreateDto(title: String, authors: String, description: String, categoryId: Long, path: String) :
    BookBaseDto(title, authors, description, categoryId, path)