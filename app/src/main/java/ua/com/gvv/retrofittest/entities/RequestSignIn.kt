package ua.com.gvv.retrofittest.entities

data class RequestSignInUser(
        val email: String,
        val password: String
)

data class RequestSignIn(
        val user: RequestSignInUser
) {
    constructor(email: String, password: String) : this(RequestSignInUser(email, password))
}

data class RequestCompany(
        val name: String
)


data class RequestCreateCompany(
        val company: RequestCompany,
        val user: RequestSignInUser
) {
    constructor(name: String, email: String, password: String) : this(RequestCompany(name), RequestSignInUser(email, password))
}



