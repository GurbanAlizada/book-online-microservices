package com.example.bookservice.dto

import com.example.bookservice.model.Book

data class BookDto @JvmOverloads constructor(

    val id: BookIdDto? = null,
    val title: String ,
    val author: String,
    val pressName: String,
    val publishYear: Int

){

    companion object {
        @JvmStatic
        fun convert(from: Book) : BookDto{
           return BookDto(
            from.id?.let { BookIdDto.convert(it , from.isbn) },
            from.title,
            from.author,
            from.pressName,
            from.publishYear
            )
        }

    }

}
