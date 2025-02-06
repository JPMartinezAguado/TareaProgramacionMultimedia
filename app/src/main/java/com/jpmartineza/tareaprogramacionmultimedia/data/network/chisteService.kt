package com.jpmartineza.tareaprogramacionmultimedia.data.network


import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class ChisteAleatorioResponse(
    @SerialName("value") val value: String
)


@Singleton
class RetrofitClient @Inject constructor() {

    private val BASE_URL = "https://api.chucknorris.io/"


    private val contentType =  MediaType.get("application/json")
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
        .build()

    val service: ChisteService = retrofit.create(ChisteService::class.java)

}

interface ChisteService {
    @GET("jokes/random")
    suspend fun obtenerChisteAleatorio(): ChisteAleatorioResponse
}

suspend fun capturarChiste(client: RetrofitClient): String {
    return client.service.obtenerChisteAleatorio().value
}

fun main() {
    runBlocking {
        val chiste = capturarChiste(RetrofitClient())
        Log.d("chiste", chiste)
    }
}
