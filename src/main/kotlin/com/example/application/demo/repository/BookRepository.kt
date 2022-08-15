package com.example.application.demo.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository : JpaRepository<Book, Long> {

    override fun findAll(pageable: Pageable): Page<Book>

    fun findByAuthor(author: String): List<Book>

    fun findBookById(id: Long): Book?
}