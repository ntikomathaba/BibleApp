package com.example.bibleapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.bibleapp.core.data.BibleDatabase
import com.example.bibleapp.feature.books.data.local.FavouriteVerseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideBibleDatabase(@ApplicationContext context: Context): BibleDatabase =
            Room.databaseBuilder(
                context,
                BibleDatabase::class.java,
                BibleDatabase.DATABASE_NAME
            ).build()


        @Provides
        fun provideFavouriteVerseDao(
            database: BibleDatabase
        ): FavouriteVerseDao {
            return database.favouriteVerseDao()
        }
    }
}
