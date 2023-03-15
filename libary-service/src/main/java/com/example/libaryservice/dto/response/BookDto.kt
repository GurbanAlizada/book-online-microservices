package com.example.libaryservice.dto.response

data class BookDto @JvmOverloads constructor(
    val id: BookIdDto? = null,
    val title: String? = null,
    val author: String? = null,
    val pressName: String? = null,
    val publishYear: Int? = 0
)
