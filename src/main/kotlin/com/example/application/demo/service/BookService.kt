package com.example.application.demo.service

import com.example.application.demo.repository.Book
import com.example.application.demo.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService @Autowired constructor(
    private val bookRepository: BookRepository
) {

    fun findAllBook():List<Book> {
        return bookRepository.findAll()
    }

    fun findBookById(id: Long): Book? {
        return bookRepository.findBookById(id)
    }

    fun findAllBooksByPage(pageable: Pageable):Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun addABook(book:Book): Book{
        return bookRepository.save(book)
    }

    fun delete(id: Long){
        bookRepository.deleteById(id)
    }

    fun findByAuthor(author: String): List<Book> {
        return bookRepository.findByAuthor(author)
    }

    fun updateBook(
        id: Long,
        author: String,
        name: String,
        description: String,
        status: Int
    ): Book {
        val newBook = Book()
        newBook.status = status
        newBook.id = id
        newBook.name = name
        newBook.description = description
        newBook.author = author

        return bookRepository.save(newBook)
    }
}