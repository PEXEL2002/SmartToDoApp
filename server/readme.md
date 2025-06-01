# 📦 Serwer aplikacji ToDo

Serwer REST API dla mobilnej aplikacji ToDo. Obsługuje zdalną bazę danych (MySQL / MariaDB), logikę użytkowników, zadania oraz autoryzację z użyciem JWT. Przystosowany do synchronizacji danych z aplikacją mobilną działającą w trybie offline.

---

## ⚙️ Technologie

- Python 3.12+
- Flask
- Flask SQLAlchemy
- Flask JWT Extended
- dotenv
- MariaDB / MySQL

---

## 📁 Struktura katalogów

```
server/
├── app/
│   ├── models/                # Modele danych (np. użytkownik, zadanie)
│   │   ├── __init__.py
│   │   ├── task_model.py
│   │   └── user_model.py
│   ├── routes/                # Endpointy API (np. logowanie, zadania)
│   ├── utils/                 # Funkcje pomocnicze
│   │   ├── __init__.py
│   │   ├── password_utils.py  # Weryfikacja haseł
│   │   └── token_utils.py     # Generowanie JWT
│   └── __init__.py            # Inicjalizacja aplikacji Flask
│
├── config.py                  # Konfiguracja aplikacji (np. odczyt z .env)
├── run.py                     # Główny plik uruchamiający serwer Flask
├── .env                       # Zmienne środowiskowe (lokalne, prywatne)
├── .env.example               # Wzór pliku środowiskowego
├── requirements.txt           # Lista zależności
└── readme.md                  # Dokumentacja backendu (ten plik)
```

---

## 🛠️ Instalacja

```
cd server
python -m venv venv
source venv/bin/activate  # lub .\venv\Scripts\activate na Windows
pip install -r requirements.txt
```

---

## ⚙️ Konfiguracja `.env`

Utwórz plik `.env` na podstawie `.env.example`:

```
cp .env.example .env
```

Przykładowa zawartość `.env`:

```
FLASK_ENV=development
SECRET_KEY=your-secret-key-here
JWT_SECRET_KEY=your-jwt-secret-here
DATABASE_URL=mysql+pymysql://username:password@localhost/todo_db
```

---

## 🚀 Uruchomienie serwera

```
python run.py
```

Serwer domyślnie działa pod adresem `http://127.0.0.1:5000/`.

---

## 🔌 Endpointy API (skrót)

| Metoda | Endpoint                  | Opis                        |
|--------|---------------------------|-----------------------------|
| POST   | /todo/api/auth/register   | Rejestracja użytkownika     |
| POST   | /todo/api/auth/login      | Logowanie i JWT             |
| GET    | /todo/api/tasks           | Pobranie listy zadań        |
| POST   | /todo/api/tasks           | Dodanie nowego zadania      |
| PUT    | /todo/api /tasks/<id>     | Edycja zadania              |
| DELETE | /todo/api/tasks/<id>      | Usunięcie zadania           |

> Szczegóły znajdują się w plikach z katalogu  [`app/routes/`](https://github.com/PEXEL2002/SmartToDoApp/tree/main/server/app/routes).

---

## 📌 Uwagi

- Serwer nie przechowuje danych lokalnie – baza SQLite znajduje się tylko na urządzeniu użytkownika.
- Synchronizacja danych między aplikacją a serwerem realizowana jest po stronie klienta.
- Zalecane jest stosowanie HTTPS oraz bezpiecznych tokenów JWT w środowisku produkcyjnym.

---
