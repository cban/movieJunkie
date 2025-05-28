package com.core.di

import com.core.datasource.MoviesDataSource
import com.core.datasource.remote.MoviesRemoteDataSource
import com.core.domain.repository.MoviesRepository
import com.core.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMoviesRemoteDataSource(
        moviesRemoteDataSource: MoviesRemoteDataSource,
    ): MoviesDataSource

    @Binds
    abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository


}