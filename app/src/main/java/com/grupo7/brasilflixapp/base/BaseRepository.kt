package com.grupo7.brasilflixapp.base

import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.api.util.ErrorUtils
import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = safeApiResult(call)

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : ResponseApi {
        try {
            val response = call.invoke()

            return if(response.isSuccessful) {
                ResponseApi.Success(response.body())
            } else {
                val error = ErrorUtils.parseError(response)

                error?.message?.let {  message ->
                    ResponseApi.Error(message)
                } ?: run {
                    ResponseApi.Error(R.string.error_default)
                }
            }
        } catch (error : Exception) {
            return ResponseApi.Error(R.string.error_default)
        }
    }
}