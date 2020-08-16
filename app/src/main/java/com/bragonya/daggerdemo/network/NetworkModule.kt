package com.bragonya.daggerdemo.network

import com.bragonya.daggerdemo.utils.BASE_URL_POKE_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    fun providePokeAPI(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL_POKE_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(PokeAPI::class.java)

    @Provides
    fun provideLogger(): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

}