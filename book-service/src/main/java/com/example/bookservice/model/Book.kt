package com.example.bookservice.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" ,strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String ,
    val author:String,
    @Column(unique = true)
    val isbn: String,
    val pressName: String,
    val publishYear: Int


)
