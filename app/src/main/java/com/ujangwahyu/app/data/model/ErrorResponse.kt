package com.ujangwahyu.app.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("documentation_url")
    val documentationUrl: String?,
)
