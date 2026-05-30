package com.example.bibleapp.feature.verse_of_the_day.presentation

sealed class VOTDEvent {
    data object GetVerseOfTheDay: VOTDEvent()
    data object FavVerse: VOTDEvent()
}