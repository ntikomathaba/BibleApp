package com.example.bibleapp.core.di

import com.example.bibleapp.feature.bible.data.repo.BibleRepoImpl
import com.example.bibleapp.feature.bible.domain.repo.BibleRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBibleRepository(
        impl: BibleRepoImpl
    ): BibleRepo
}