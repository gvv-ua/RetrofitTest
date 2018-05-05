package ua.com.gvv.retrofittest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ua.com.gvv.retrofittest.entities.RequestCreateCompany
import ua.com.gvv.retrofittest.entities.RequestSignIn
import ua.com.gvv.retrofittest.entities.ResponseUser

interface ApiService {

    @POST("/api/sign-in")
    fun signIn(@Body requestSignIn: RequestSignIn): Call<ResponseUser>

    @POST("/api/companies")
    fun createCompany(@Body requestCreateCompany: RequestCreateCompany): Call<ResponseUser>

    companion object Factory {
        private const val BASE_URL = "http://lookset.com.ua"
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                    //.addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            retrofit.create(ApiService::class.java)
        }
    }

}