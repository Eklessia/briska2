package cl.malditosnakamas.briska.network

import cl.malditosnakamas.briska.about.data.remote.AboutApi
import cl.malditosnakamas.briska.about.domain.About
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHandler {

    @JvmStatic
    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("http://192.168.0.30:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    fun getAboutApi(): AboutApi {
        return getRetrofit().create(AboutApi::class.java)
    }
}