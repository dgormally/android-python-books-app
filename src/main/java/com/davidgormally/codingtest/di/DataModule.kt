package com.davidgormally.codingtest.di

import com.davidgormally.codingtest.data.mapper.BookMapper
import com.davidgormally.codingtest.data.remote.BookRemoteDataSourceImpl
import com.davidgormally.codingtest.data.repository.BookRepositoryImpl
import com.davidgormally.codingtest.domain.datasource.BookRemoteDataSource
import com.davidgormally.codingtest.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindBookRemoteDataSource(bookRemoteDataSourceImpl: BookRemoteDataSourceImpl): BookRemoteDataSource

    @Binds
    abstract fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

    companion object {
        @Provides
        @Singleton
        fun provideBookMapper(): BookMapper {
            return BookMapper
        }
    }
}
