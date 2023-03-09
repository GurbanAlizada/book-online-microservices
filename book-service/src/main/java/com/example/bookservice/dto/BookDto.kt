package com.example.bookservice.dto

import com.example.bookservice.model.Book
import java.util.*

data class BookDto @JvmOverloads constructor(

    val id: BookIdDto? = null,
    val title: String ,
    val author: String,
    val pressName: String,
    val date: Date

){

    companion object {
        @JvmStatic
        fun convert(from: Book) : BookDto{
           return BookDto(
            from.id?.let { BookIdDto.convert(it , from.isbn) },
            from.title,
            from.author,
            from.pressName,
            from.date
            )
        }

    }

}
