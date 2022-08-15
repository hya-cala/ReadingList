package com.example.application.demo.repository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var author: String? = null
    var name: String? = null
    var status = 0
    var description: String? = null

    constructor(){}

    data class Developer(val id:Long, var author: String?, var name: String?, var status: Int, var description: String?)
}