package com.example.bibleapp.core.di

import android.content.Context
import com.example.bibleapp.feature.books.data.local.BibleBooksDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBibleBooksDataSource(
        @ApplicationContext context: Context,
        gson: Gson
    ): BibleBooksDataSource {
        return BibleBooksDataSource(context, gson)
    }
}