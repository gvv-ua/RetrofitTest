package ua.com.gvv.retrofittest

import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException


object ApiUtil {

    fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null) {
                request.writeTo(buffer)
            } else {
                return ""
            }
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }
}