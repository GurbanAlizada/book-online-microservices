package com.example.bookservice.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "books")
data class Book @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" ,strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String ,
    val author:String,
    val isbn: String,
    val pressName: String,
    val publishYear: Int


)
