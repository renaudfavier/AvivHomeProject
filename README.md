# Real Estate Listing App

A modern Android application that displays real estate listings and their details, built with 100% Kotlin.

## Overview

This Android application provides users with a streamlined interface to browse real estate listings and view detailed information about each property. The app demonstrates modern Android development practices, clean architecture principles, and effective use of the latest Android technologies.

## Features

- **Listing Screen**
  - Displays a scrollable list of real estate properties
  - Clean and intuitive user interface
  - Smooth loading and error handling

- **Details Screen**
  - Comprehensive property information
  - Smooth navigation back to listing

## Technical Specifications

### Architecture
- MVVM (Model-View-ViewModel) architecture pattern
- Clean Architecture principles
- Repository pattern for data management
- Navigation Compose

### Technologies & Libraries
- **Language**: 100% Kotlin
- **UI**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Asynchronous Programming**: Kotlin Coroutines + Flow
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Compose
- **Testing**: JUnit4, Mockito, Compose UI Test

### API Endpoints
- Listings: `https://gsl-apps-technical-test.dignp.com/listings.json`
- Details: `https://gsl-apps-technical-test.dignp.com/listings/{listingId}.json`

## Project Setup

1. Clone the repository
```bash
git clone https://github.com/renaudfavier/AvivHomeProject
```

2. Open the project in Android Studio

3. Make sure it correctly created `local.properties` directing to your Android SDK, you will need version 35

4. Build and run the project
```bash
./gradlew build
```

## Running Tests

Execute the following commands to run tests:

```bash
# Unit tests
./gradlew test

# Instrumentation tests
./gradlew connectedAndroidTest
```

## Project Structure

```
core/
├── data/
├── di/
├── presentation/
│   ├── listings/
│   └── details/
└── ui/
    └── theme/
property/
├── data/
│   ├── di/
│   ├── mapper/
│   ├── model/
│   └── network/
├── domain/
│   └── model/
└── presentation/
    ├── component/
    ├── detail/
    └── list/

```

## Contact

Renaud Favier - renaud.favier.blimer@gmail.com
