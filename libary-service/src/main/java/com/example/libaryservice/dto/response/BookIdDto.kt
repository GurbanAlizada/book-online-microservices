package com.example.libaryservice.dto.response

data class BookIdDto @JvmOverloads constructor(

    val id: String? = "",
    val isbn: String?= "" ,

){
    companion object{

        fun convert(id: String? , isbn: String?):BookIdDto{
            return BookIdDto(id, isbn)
        }

    }

}
