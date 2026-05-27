package com.example.bibleapp.feature.bible.data.mapper

import com.example.bibleapp.feature.bible.data.remote.dto.BibleVerseResponseDto
import com.example.bibleapp.feature.bible.data.remote.dto.VerseDto
import com.example.bibleapp.feature.bible.domain.model.BibleVerse
import com.example.bibleapp.feature.bible.domain.model.Verse

fun BibleVerseResponseDto.toDomain(): BibleVerse {
    return BibleVerse(
        reference = reference,
        text = text,
        translationName = translationName,
        translationNote = translationNote,
        verses = verses.map { it.toDomain() }
    )
}

fun VerseDto.toDomain(): Verse {
    return Verse(
        bookName = bookName,
        chapter = chapter,
        text = text,
        verse = verse
    )
}
