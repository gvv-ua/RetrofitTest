package ua.com.gvv.retrofittest.entities

data class ResponseUser (val user: User)

data class ResponseError(
        val status: Int,
        val error: String,
        val message: String
)