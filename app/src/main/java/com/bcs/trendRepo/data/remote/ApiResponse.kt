package com.bcs.trendRepo.data.remote

import retrofit2.Response


@Suppress("unused")
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            var errorMsg = error.message ?: "unknown error"
            errorMsg =
                if (errorMsg.contains("Unable to resolve host")) "Couldn't connect! Please try again." else errorMsg
            return ApiErrorResponse(errorMsg)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return when (response.code()) {
                in 200..399 -> {

                    val body = response.body()
                    if (body == null || response.code() == 204) {
                        ApiEmptyResponse()
                    } else {
                        ApiSuccessResponse(body = body)
                    }
                }

                401 -> {
                    var em = "${response.message()}"
                    val msg = response.errorBody()?.string()
                    if (response.message().isNotEmpty()) em += "\n"
                    if (msg != null && msg.contains("\"")) {
                    } else em += msg

                    ApiErrorResponse(em)
                }

                in 500..511 -> ApiErrorResponse(response.message())

                else -> {
                    val msg = response.errorBody()?.string()
                    ApiErrorResponse(msg ?: "unknown error")
                }
            }

        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()