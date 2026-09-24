package com.davidgormally.codingtest.di

import com.davidgormally.codingtest.domain.repository.BookRepository
import com.davidgormally.codingtest.domain.usecase.GetPopularBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetPopularBooksUseCase(bookRepository: BookRepository): GetPopularBooksUseCase {
        return GetPopularBooksUseCase(bookRepository)
    }
}
