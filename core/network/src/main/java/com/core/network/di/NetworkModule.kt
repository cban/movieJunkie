package com.core.network.di

import com.core.network.BuildConfig
import com.core.network.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkConfig(): NetworkConfig {
        return NetworkConfig(
            baseUrl = BuildConfig.BASE_URL,
            enableLogging = BuildConfig.DEBUG,
            headers = mapOf(
                "Authorization" to "Bearer ${BuildConfig.token}",
                "Accept" to "application/json"
            )
        )
    }
}