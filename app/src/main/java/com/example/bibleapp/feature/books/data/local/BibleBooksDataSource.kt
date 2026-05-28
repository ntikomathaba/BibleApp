package com.example.bibleapp.feature.books.data.local

import android.content.Context
import com.example.bibleapp.feature.books.domain.model.Books
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject

class BibleBooksDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    private val typeBooks: Type = object : TypeToken<Books>() {}.type

    companion object {
        private const val BIBLE_BOOKS_JSON_FILE = "bible_books_list.json"
    }

    fun getBibleBooksFromAsset(fileName: String = BIBLE_BOOKS_JSON_FILE): Books {
        return try {
            val json = readString(context, fileName)
            gson.fromJson(json, typeBooks)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun readString(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioE: IOException) {
            ioE.printStackTrace()
            null
        }
    }
}

