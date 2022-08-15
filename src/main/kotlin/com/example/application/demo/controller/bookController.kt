package com.example.application.demo.controller

import com.example.application.demo.repository.Book
import com.example.application.demo.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class bookController @Autowired constructor(
    private val bookService: BookService
) {
    @GetMapping("/books")
    fun list(
        @PageableDefault(size=5, sort=["id"], direction = Sort.Direction.DESC) pageable: Pageable,
        model: Model,
    ) : String {
        val books = bookService.findAllBooksByPage(pageable)
        model.addAttribute("page", books)
        return "books"
    }

    @GetMapping("/books/{id}")
    fun detail(@PathVariable id: Long, model: Model): String {
        val book: Book = bookService.findBookById(id) ?: return "error"
        model.addAttribute("book", book)
        return "book"
    }

    @GetMapping("/books/input")
    fun inputPage(model: Model): String {
        model.addAttribute("book", Book())
        return "input"
    }

    @PostMapping("/books")
    fun addNewBook(book: Book, redirectAttributes: RedirectAttributes): String {
        val newBook = bookService.addABook(book)
        if (newBook != null) {
            redirectAttributes.addFlashAttribute("message", "Successfully updated")
        }
        return "redirect:/books"
    }

    @GetMapping("/books/{id}/input")
    fun editBook(@PathVariable id:Long, model: Model): String {
        val book = bookService.findBookById(id)
        println("for debug")
        println(book!!.id)
        model.addAttribute("book", book)
        return "input"
    }

    @GetMapping("/books/{id}/delete")
    fun deleteBook(@PathVariable id:Long):String {
        bookService.delete(id)
        return "redirect:/books"
    }
}