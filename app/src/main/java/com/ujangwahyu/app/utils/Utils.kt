package com.ujangwahyu.app.utils

import com.google.gson.Gson
import com.ujangwahyu.app.data.model.ErrorResponse
import retrofit2.HttpException

object Utils {

    fun errorMapper(response: HttpException?): ErrorResponse? {
        val errorBody = response?.response()?.errorBody()?.string()
        return Gson().fromJson(errorBody, ErrorResponse::class.java)
    }
}