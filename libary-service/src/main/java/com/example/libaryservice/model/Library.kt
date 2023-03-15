package com.example.libaryservice.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*


@Entity
@Table(name = "libaries")
data class Library @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",


    @ElementCollection
    val userBook: List<String> = ArrayList()

)
