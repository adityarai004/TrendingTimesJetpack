# Showcase News App with Jetpack Compose and Clean Architecture 🚀

Welcome to **Showcase News App**—an Android application built using Jetpack Compose and the Clean Architecture design pattern. This app was initially crafted with XML and MVVM, but has now evolved into a modern, Compose-based masterpiece. It leverages the power of Clean Architecture for maintainable and scalable code while consuming The TrendingTimesBackend API created in-house and hosted on Render, to create a unique and engaging marketplace experience.

## Features

- **Modern UI**: Built entirely with Jetpack Compose, providing a smooth and responsive user experience.
- **Clean Architecture**: Separation of concerns is key, with distinct layers for UI, Domain, and Data.
- **Use Case Design Pattern**: Ensuring business logic is encapsulated in use cases for better testability and reuse.
- **Custom API**: Backend created using Node.js, Express.js, MongoDB, and Render, handling any number of requests with CRON jobs to keep the free server up and running.
- **Firebase Push Notifications**: Integrated Firebase for seamless push notification delivery.
- **Bookmarks Functionality**: Implemented a bookmarking system using a custom backend, allowing users to save and retrieve their favorite content.

## Tech Stack

### Core Technologies
- **Kotlin**: The primary language for Android development, loved for its expressive syntax and null safety.
- **Jetpack Compose**: The modern toolkit for building native UI, replacing XML with a more declarative approach.
- **Clean Architecture**: Ensures a robust, testable, and scalable codebase.

### Jetpack and AndroidX Libraries
- **Android KTX**: Enhances Android APIs with idiomatic Kotlin extensions.
- **Lifecycle**: Manages UI components according to their lifecycle, reducing memory leaks and crashes.
- **ViewModel**: Stores and manages UI-related data in a lifecycle-conscious way.

### Networking
- **Ktor**: Powerful libraries for making HTTP requests, handling RESTful APIs with ease.

### Asynchronous Programming
- **Kotlin Coroutines**: Simplifies asynchronous programming, allowing for efficient and readable code.
- **Flow**: A stream of data that emits sequential values, perfect for handling live updates.

### Dependency Injection
- **Hilt**: Simplifies dependency injection, reducing boilerplate and enhancing code modularity.

### Database
- **Room**: A robust library for managing SQLite databases with ease, perfect for offline support.

### Image Loading
- **Coil**: An efficient and modern image loading library that integrates seamlessly with Jetpack Compose.

### UI/UX Components
- **Material Compose**: Implements Material Design components, ensuring a consistent and polished user experience.
- **Accompanist Pager**: Adds intuitive and customizable page navigation to your app.

### CI/CD
- **CodeMagic**: Automates your workflows, enabling continuous integration and deployment right from GitHub.

## Demo

🚀 **[Check out the live demo here!](#)** 🚀

<img src="https://github.com/adityarai004/TrendingTimes/assets/86659578/e5f15536-4bb1-4a02-a8f2-709df388cafe" width="250"/>
<img src="https://github.com/adityarai004/TrendingTimes/assets/86659578/675b55c1-6a33-4578-9779-badc58c4f9c4" width="250"/>
<img src="https://github.com/adityarai004/TrendingTimes/assets/86659578/23f75ee7-0f97-456a-93db-0be7ac112aa7" width="250"/>
<img src="https://github.com/adityarai004/TrendingTimes/assets/86659578/61356c01-07c3-447e-bb5b-7838812b3d82" width="250"/>

## Getting Started

Clone the repo and follow these instructions to get the app up and running:

```bash
git clone https://github.com/adityarai004/TrendingTimesJetpack.git
