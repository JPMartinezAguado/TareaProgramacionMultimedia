package com.jpmartineza.tareaprogramacionmultimedia.data.network


import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@JsonClass(generateAdapter = true)
data class ChisteAleatorioResponse(
    val value: String
)


@Singleton
class RetrofitClient @Inject constructor() {

    private val BASE_URL = "https://api.chucknorris.io/"

    private val moshi = Moshi.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: ChisteService = retrofit.create(ChisteService::class.java)

}

interface ChisteService {
    @GET("jokes/random")
    suspend fun obtenerChisteAleatorio(): ChisteAleatorioResponse

}



