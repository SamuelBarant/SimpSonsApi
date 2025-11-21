package barant.curso.simpsonsapi.core.di.modules

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    val BASE_URL = "https://thesimpsonsapi.com/api"
    factory { HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }}

    factory { OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()}

    factory { Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()}
}