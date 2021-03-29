package com.omusiiko.coctaildb.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ExpandInterceptor(private val contentType: String, private val devicePlatform: String) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url().newBuilder().addQueryParameter("format", "json").build()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", contentType)
            .addHeader("Device-Platform", devicePlatform)
            .url(url)
            .build()
        val response = chain.proceed(modifiedRequest)
        Log.d("RESPONSE_CODE", response.code().toString())
        return response
    }
}