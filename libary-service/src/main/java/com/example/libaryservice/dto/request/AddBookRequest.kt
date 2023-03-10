package com.example.libaryservice.dto.request

import javax.validation.constraints.NotBlank

data class AddBookRequest(

    @field:NotBlank
    val id: String ,

    @field:NotBlank
    val isbn: String

)
