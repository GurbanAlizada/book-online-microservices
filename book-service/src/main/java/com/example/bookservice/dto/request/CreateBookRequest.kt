package com.example.bookservice.dto.request

import javax.validation.constraints.NotBlank

data class CreateBookRequest (

    @field:NotBlank(message = "Bu hisse bosh ola bilmez")
    val title: String ,

    @field:NotBlank(message = "Bu hisse bosh ola bilmez")
    val author:String,

    @field:NotBlank(message = "Bu hisse bosh ola bilmez")
    val isbn: String,

    @field:NotBlank(message = "Bu hisse bosh ola bilmez")
    val pressName: String,

    val publishYear: Int

    ){


}
