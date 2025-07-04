package com.core.network.di

import com.core.network.MoviesNetworkDataSource
import com.core.network.NetworkConfig
import com.core.network.retrofit.MoviesApiService
import com.core.network.retrofit.RetrofitNetworkDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNetworkModule {

    @Provides
    fun provideLoggingInterceptor(config: NetworkConfig): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (config.enableLogging) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Provides
    fun provideHeaderInterceptor(config: NetworkConfig): Interceptor =
        Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()

            config.headers.forEach { (key, value) ->
                builder.addHeader(key, value)
            }

            chain.proceed(builder.build())
        }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        config: NetworkConfig
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideMoviesApiService(
        retrofit: Retrofit
    ): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    fun provideRetrofitNetworkDataSource(
        apiService: MoviesApiService
    ): MoviesNetworkDataSource {
        return RetrofitNetworkDataSource(apiService)
    }
}