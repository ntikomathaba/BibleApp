package com.example.bibleapp.core.di

import com.example.bibleapp.feature.bible_search.data.repo.SearchBibleRepoImpl
import com.example.bibleapp.feature.bible_search.domain.repo.SearchBibleRepo
import com.example.bibleapp.feature.books.data.repo.BibleBookRepoImpl
import com.example.bibleapp.feature.books.domain.repo.BibleBookRepo
import com.example.bibleapp.feature.verse_of_the_day.data.repo.VerseOfTheDayImpl
import com.example.bibleapp.feature.verse_of_the_day.domain.repo.VerseOfTheDayRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBibleRepository(
        impl: SearchBibleRepoImpl
    ): SearchBibleRepo

    @Binds
    abstract fun bindBibleBookRepo(
        impl: BibleBookRepoImpl
    ): BibleBookRepo

    @Binds
    abstract fun bindFVerseOfTheDayRepo(
        impl: VerseOfTheDayImpl
    ): VerseOfTheDayRepo
}