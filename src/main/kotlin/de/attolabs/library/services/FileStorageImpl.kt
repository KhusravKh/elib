package de.attolabs.library.services

import de.attolabs.library.services.abstractions.FileStorage
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.io.path.isExecutable

@Service
class FileStorageImpl : FileStorage {

    val rootLocation = Paths.get("src/main/resources/static/images")

    override fun store(file: MultipartFile): String? {
        Files.copy(file.inputStream, this.rootLocation.resolve(file.originalFilename))
        return file.originalFilename
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun init() {
        if (!rootLocation.isExecutable()) {
            Files.createDirectory(rootLocation)
        }
    }

    override fun loadFiles(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
            .filter { path -> !path.equals(this.rootLocation) }
            .map(this.rootLocation::relativize)
    }
}