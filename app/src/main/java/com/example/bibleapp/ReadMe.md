# Bible App

A modern Android Bible application built using Jetpack Compose and Clean Architecture principles.
The app integrates with the Bible API to allow users to browse books, chapters, verses, and save favourite scriptures locally.

---

# Features

* Browse all books of the Bible
* View chapters for each book
* Read verses for selected chapters
* Swipe horizontally between verses
* Save favourite verses locally
* Swipe-to-delete bookmarks
* Bottom navigation support
* Modern Material 3 UI
* Responsive Jetpack Compose layouts
* Reactive UI using Kotlin Flows and StateFlow

---

# Tech Stack

## Architecture

* Clean Architecture
* MVVM (Model-View-ViewModel)

## UI

* Jetpack Compose
* Material 3
* Navigation Compose

## Dependency Injection

* Hilt

## Networking

* Retrofit
* OkHttp
* Gson

## Local Storage

* Room Database

## Reactive Programming

* Kotlin Coroutines
* Flow / StateFlow

---

# API Used

This project uses the public Bible API:

https://bible-api.com/

Examples:

* `/data/web`
* `/data/web/JHN`
* `/data/web/JHN/3`

---

# App Flow

Books → Chapters → Verses → Verse Reader

Users can:

1. Select a book
2. Select a chapter
3. Read all verses
4. Swipe between verses
5. Save favourite scriptures

---

# Project Structure

The project follows a feature-based Clean Architecture structure.

```text
feature_bible/
│
├── data/
│   ├── remote/
│   ├── local/
│   ├── dto/
│   ├── mapper/
│   └── repository/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
└── presentation/
    ├── books/
    ├── chapters/
    ├── verses/
    ├── bookmarks/
    └── components/
```

---

# Architectural Decisions

## Clean Architecture

The project separates concerns into:

* Presentation
* Domain
* Data

This improves:

* scalability
* maintainability
* testability

---

## Reactive State Management

The app uses:

* `StateFlow`
* `Flow`
* `collectAsStateWithLifecycle`

This ensures the UI reacts automatically to database and network updates.

---

## Offline Persistence

Favourite verses are stored locally using Room Database and automatically observed using Flows.

---

# Notable Features

## Horizontal Verse Reader

Users can swipe horizontally between verses using `HorizontalPager`.

## Swipe to Delete

Bookmarks support swipe-to-delete interactions using Compose's `SwipeToDismissBox`.

## Dynamic Navigation

Navigation arguments are used to dynamically load:

* books
* chapters
* verses

---

# Improvements I Would Add With More Time

* Search functionality
* Verse sharing
* Multiple Bible translations
* Dark mode customization
* Font size preferences
* Verse highlighting
* Audio Bible support
* Unit/UI testing
* Pagination and caching

---

# Screenshots

(Add screenshots here if required)

---

# How To Run

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run the app

Minimum SDK: 24

---

# Author

Ntiko Mathaba

Android Developer
