package de.attolabs.library

import de.attolabs.library.services.abstractions.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@SpringBootApplication
class LibraryApplication

fun main(args: Array<String>) {
    runApplication<LibraryApplication>(*args)
}

@Component
class FileStoreRuner(private val fileStorage: FileStorage) {

    @PostConstruct
    fun run() {
        fileStorage.init()
    }

}
