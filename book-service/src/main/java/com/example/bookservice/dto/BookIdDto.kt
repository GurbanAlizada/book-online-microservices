package com.example.bookservice.dto

data class BookIdDto @JvmOverloads constructor(

    val id: String? = "",
    val title: String ,

){

    companion object{
        @JvmStatic
        fun convert(id:String , title: String): BookIdDto{
            return BookIdDto(id , title)
        }

    }

}