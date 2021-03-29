package com.omusiiko.coctaildb.retrofit

import com.omusiiko.coctaildb.common.COCKTAILS_DB_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private var retrofit: Retrofit? = null

    init {
        val interceptor = ExpandInterceptor("application/json", "android")
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(COCKTAILS_DB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getCoctailDBApi(): CocktailDBApi = retrofit!!.create(CocktailDBApi::class.java)

}