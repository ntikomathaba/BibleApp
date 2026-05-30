package com.example.bibleapp.feature.books.data.mapper

import com.example.bibleapp.feature.books.data.remote.dto.BibleBookDto
import com.example.bibleapp.feature.books.data.remote.dto.ChapterDto
import com.example.bibleapp.feature.books.data.remote.dto.VerseDto
import com.example.bibleapp.feature.books.domain.model.BibleBook
import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.model.Chapter
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Testament
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.domain.model.Verses

fun List<BibleBookDto>.toDomain(): Books {
    return Books(
        books = this.map {
            BibleBook(
                name = it.name,
                url = it.url,
                id = it.id,
                testament = Testament.NONE
            )
        }
    )
}

fun List<ChapterDto>.toDomain(): Chapters {
    return Chapters(
        chapters = this.map {
            Chapter(
                id = it.id,
                book = it.book,
                chapter = it.chapter,
                url = it.url
            )
        }
    )
}

fun List<VerseDto>.toDomain(): Verses {
    return Verses(
        verses = this.map {
            Verse(
                id = it.id,
                book = it.book,
                chapter = it.chapter,
                verse = it.verse,
                text = it.text
            )
        }
    )
}

fun BibleBookDto.toTestamentBook(testament: Testament): BibleBook {
    return BibleBook(
        id = id,
        name = name,
        url = url,
        testament = testament
    )
}