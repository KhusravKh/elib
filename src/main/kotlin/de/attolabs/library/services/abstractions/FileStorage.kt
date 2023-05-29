package de.attolabs.library.services.abstractions

import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FileStorage {
    fun store(file: MultipartFile): String?
    fun deleteAll()
    fun init()
    fun loadFiles(): Stream<Path>
}