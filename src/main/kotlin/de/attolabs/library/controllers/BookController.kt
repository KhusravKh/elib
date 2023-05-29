package de.attolabs.library.controllers

import de.attolabs.library.dto.book.BookCreateDto
import de.attolabs.library.services.abstractions.BookService
import de.attolabs.library.services.abstractions.CategoryService
import de.attolabs.library.services.abstractions.FileStorage
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Min

@Controller
class BookController(private val bookService: BookService, private val categoryService: CategoryService, private val fileStorage: FileStorage) {

    @GetMapping("/")
    fun first(model: Model): String {
        return "redirect:/index"
    }

    @GetMapping("/index/{id}", "/index")
    fun blog(
        @PathVariable("id") id: Long?,
        @RequestParam("page", defaultValue = "0") @Min(
            value = 0,
            message = "Page index must not be less than zero."
        ) page: Int,
        model: Model
    ): String {
        model["menuId"] = id ?: 0
        model.addAttribute("books", bookService.getAllPaginated(id ?: 0, page))
        model.addAttribute("categories", categoryService.getAll())
        return "blog"
    }

    @GetMapping("/admin")
    fun admin(model: Model): String {
        model["menu"] = -1
        model.addAttribute("book", BookCreateDto("", "", "", 0, ""))
        model.addAttribute("categories", categoryService.getAll())
        return "admin"
    }

    @PostMapping("/sendBook")
    fun save(@ModelAttribute("book") bookCreateDto: BookCreateDto, @RequestParam("image") image: MultipartFile): String {
        bookService.save(bookCreateDto, image)
        return "redirect:/index/${bookCreateDto.categoryId}"
    }

}