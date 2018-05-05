package ua.com.gvv.retrofittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.com.gvv.retrofittest.entities.RequestCreateCompany
import ua.com.gvv.retrofittest.entities.RequestSignIn
import ua.com.gvv.retrofittest.entities.ResponseError
import ua.com.gvv.retrofittest.entities.ResponseUser


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bSend.setOnClickListener { doLogin() }
        bSendCompany.setOnClickListener { doSendCompany() }
    }

    private fun doSendCompany() {
        ApiService.instance.createCompany(RequestCreateCompany("company", "john@ukr.net", "123456")).enqueue(object : Callback<ResponseUser> {
            override fun onFailure(call: Call<ResponseUser>?, t: Throwable?) {
                Log.d(TAG, "Failure")
            }

            override fun onResponse(call: Call<ResponseUser>?, response: Response<ResponseUser>?) {
                Log.d(TAG, "Success ${call?.request()?.url()}")
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                    } else {
                        val errorBody = response.errorBody()
                        Log.d(TAG, errorBody?.string().toString())
                        if (errorBody != null) {
                            val moshi = Moshi.Builder().build()
                            val errorAdapter = moshi.adapter(ResponseError::class.java)
                            val error =  errorAdapter.fromJson(errorBody.string())
                            Log.d(TAG, error.toString())
                        }
                    }
                }
            }
        })
    }

    private fun doLogin() {
        ApiService.instance.signIn(RequestSignIn("john@ukr.net", "123456")).enqueue(object : Callback<ResponseUser> {
            override fun onFailure(call: Call<ResponseUser>?, t: Throwable?) {
                Log.d(TAG, "Failure")
            }

            override fun onResponse(call: Call<ResponseUser>?, response: Response<ResponseUser>?) {
                Log.d(TAG, "Success ${call?.request()?.url()}")
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val moshi = Moshi.Builder().build()
                            val errorAdapter = moshi.adapter(ResponseError::class.java)
                            val error =  errorAdapter.fromJson(errorBody.string())
                            Log.d(TAG, error.toString())
                        }
                    }
                }
            }
        })
    }
}
