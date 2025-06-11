# 📱 SmartToDoApp – Aplikacja mobilna (Jetpack Compose + Room)

Moduł mobilny aplikacji SmartToDoApp zbudowany z użyciem Kotlin i Jetpack Compose. Umożliwia tworzenie i zarządzanie zadaniami offline, z lokalną bazą danych SQLite (Room). Synchronizacja z backendem (Flask API + JWT) zostanie dodana w kolejnych etapach rozwoju projektu.

---

## 📁 Struktura katalogów `com.smartToDo`
```
com.smartToDo/
├── data/
│   ├── local/
│   │   ├── TaskDao.kt
│   │   ├── TaskDatabase.kt
│   │   └── TaskEntity.kt
│   └── repository/
│       └── TaskRepository.kt
├── model/
│   ├── Task.kt
│   └── TaskMapper.kt
├── ui/
│   ├── components/
│   │   ├── DeadlinePicker.kt
│   │   ├── FloatingButtons.kt
│   │   ├── PriorityDropdown.kt
│   │   ├── ResponsiveContainer.kt
│   │   └── TaskCard.kt
│   ├── screens/
│   │   ├── AddTaskScreen.kt
│   │   ├── EditTaskScreen.kt
│   │   ├── EmptyScreen.kt
│   │   └── TaskListScreen.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── SmartToDoTheme.kt
│   │   ├── Type.kt
│   │   └── NavGraph.kt
├── utils/                   (pusty)
├── viewmodel/
│   └── TaskViewModel.kt
└── MainActivity.kt
```

### 🔹 `data/local`
Warstwa dostępu do lokalnej bazy danych z wykorzystaniem Room.
- `TaskDao.kt` – definicja operacji na bazie danych (CRUD).
- `TaskDatabase.kt` – konfiguracja bazy danych Room.
- `TaskEntity.kt` – definicja encji zadań zapisywanych lokalnie.

### 🔹 `data/repository`
- `TaskRepository.kt` – logika pośrednicząca między Room a warstwą ViewModel. Synchronizacja z backendem będzie tu zaimplementowana później.

### 🔹 `model`
- `Task.kt` – model domenowy reprezentujący zadanie.
- `TaskMapper.kt` – mapowanie między encjami Room a modelem `Task`.

### 🔹 `ui/components`
Reużywalne komponenty interfejsu użytkownika.
- `DeadlinePicker.kt` – wybór daty zakończenia zadania.
- `FloatingButtons.kt` – przyciski akcji FAB.
- `PriorityDropdown.kt` – selektor poziomu priorytetu.
- `ResponsiveContainer.kt` – kontener dostosowujący layout.
- `TaskCard.kt` – widok pojedynczego zadania.

### 🔹 `ui/screens`
Główne ekrany aplikacji.
- `AddTaskScreen.kt` – formularz dodawania nowego zadania.
- `EditTaskScreen.kt` – formularz edycji istniejącego zadania.
- `EmptyScreen.kt` – ekran pustej listy zadań.
- `TaskListScreen.kt` – główny ekran listy zadań.

### 🔹 `ui/theme`
Definicje wyglądu aplikacji.
- `Color.kt`, `Type.kt` – kolory i typografia.
- `SmartToDoTheme.kt` – motyw aplikacji.
- `NavGraph.kt` – nawigacja pomiędzy ekranami (Compose Navigation).

### 🔹 `viewmodel`
- `TaskViewModel.kt` – ViewModel obsługujący stan UI i połączenie z `TaskRepository`.

### 🔹 `MainActivity.kt`
- Główna aktywność uruchamiająca Compose UI i nawigację.

---

## 🛠 Technologie

- Kotlin
- Jetpack Compose (Material 3)
- Room (SQLite)
- Navigation Compose
- ViewModel + State

---

## 🔄 Synchronizacja (planowana)

Aplikacja działa obecnie w trybie offline z lokalną bazą danych. Synchronizacja z backendem (Flask + JWT + MariaDB) zostanie dodana w kolejnych etapach projektu i będzie realizowana przez `TaskRepository.kt`.

---

## 📌 Wymagania

- Android Studio Giraffe lub nowszy
- Emulator lub urządzenie z Androidem 8.0+
- Backend uruchomiony lokalnie lub zdalnie (szczegóły: [`/server`](https://github.com/PEXEL2002/SmartToDoApp/tree/main/server))
