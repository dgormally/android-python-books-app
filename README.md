# 📚 Popular Programming Books App

A clean Android application demonstrating **MVI architecture**, **Kotlin Coroutines**, **Retrofit**, **Hilt**, and **Jetpack Compose** for displaying programming books from the OpenLibrary API.

## 🏗️ Architecture

**Clean Architecture** with clear layer separation:
- **UI Layer**: Jetpack Compose + MVI (Intent → ViewModel → UiState) + StateFlow
- **Domain Layer**: Use Cases + Repository Interfaces + Business Models  
- **Data Layer**: Repository Implementations + DTOs + API Services

## 🛠️ Tech Stack

- **Kotlin** + **Jetpack Compose** + **Material Design 3**
- **MVI** + **Kotlin Coroutines** + **StateFlow**
- **Hilt** for dependency injection
- **Retrofit** + **Gson** + **OkHttp** + **Coil**
- **MockK** + **JUnit** + **kotlinx-coroutines-test** for testing

## 🚀 Features

- Browse Python programming books from OpenLibrary API
- Book details in modal bottom sheet
- Loading states and error handling
- Modern Material Design 3 UI
- Comprehensive unit test coverage
- Compose previews for all UI components

## 🧪 Testing

- **16 unit tests** covering all layers
- **MockK** for Kotlin-first mocking (including `coEvery` for suspend functions)
- **Domain, Data, and UI layer** test coverage
- **Coroutines testing** with `runTest` and proper async handling

## 🏃‍♂️ Getting Started

```bash
# Build the project
./gradlew assembleDebug

# Run tests
./gradlew testDebugUnitTest
```

## 📁 Key Components

- **Clean Architecture** - Repository pattern, use cases, dependency inversion
- **MVI** - Single `BookUiState`, sealed `BookIntent`, and `onIntent` on the ViewModel (`viewModelScope`)
- **Dependency Injection** - Hilt modules for clean architecture
- **API Integration** - OpenLibrary API with suspend functions and proper error handling
- **Modern UI** - Jetpack Compose with Material Design 3

---

**Demonstrates clean architecture principles, modern Android development with Kotlin Coroutines, and comprehensive testing strategies.**
