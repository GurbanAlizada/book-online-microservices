package com.example.libaryservice.dto.response

data class BookDto @JvmOverloads constructor(
                                            val id: BookIdDto? = null,
                                            val title: String? = "",
                                            val author: String? = "",
                                            val pressName: String? = "",
                                            val publishYear: Int?=0 ){


    fun aaa(){

            }
}
