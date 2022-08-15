package com.example.application.demo.controller

import com.example.application.demo.repository.Book
import com.example.application.demo.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class RestController @Autowired constructor(
    private val bookService: BookService
){

    @GetMapping("/hello")
    fun sayHello(model: Model): String{
        return "Hello World"
    }

    @GetMapping("/books")
    fun getAllBooks(@RequestParam(defaultValue = "0") page:Int, @RequestParam(defaultValue = "5") size: Int):Page<Book>? {
        val sort: Sort = Sort.by(Sort.Direction.DESC, "id")
        return bookService.findAllBooksByPage(PageRequest.of(page, size, sort));
    }

    @GetMapping("/books/{id}")
    fun getBookById(@PathVariable id:Long): Book? {
        return bookService.findBookById(id)
    }

    @PostMapping("/books")
    fun addABook(@RequestParam name:String,
                 @RequestParam author: String?,
                 @RequestParam description:String?,
                 @RequestParam status: Int): Book{
        val book = Book()
        book.author = author
        book.name = name
        book.description = description
        book.status = status
        return bookService.addABook(book)
    }

    @PutMapping("books/{id}")
    fun updateBook(
        @PathVariable id: Long,
        @RequestParam name: String,
        @RequestParam author: String,
        @RequestParam description: String,
        @RequestParam status: Int
    ): Book {
        return bookService.updateBook(
            id=id,
            name=name,
            author=author,
            description = description,
            status=status,
        )
    }

    @DeleteMapping("/books/{id}")
    fun deleteABook(@PathVariable id: Long) {
        bookService.delete(id)
    }

}