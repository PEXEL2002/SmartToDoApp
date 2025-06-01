
# 📌 Dokumentacja API – Aplikacja ToDo

To REST API służy do zarządzania użytkownikami i zadaniami w aplikacji ToDo. Komunikuje się poprzez format JSON i używa JWT do autoryzacji.

---

## 🔐 Endpointy autoryzacji (`/auth`)

### ✅ POST `/auth/register` – Rejestracja

**Opis**: Rejestruje nowego użytkownika. Hasło musi być już zahashowane po stronie klienta.

**Treść żądania (JSON):**
```json
{
  "username": "użytkownik",
  "email": "email@example.com",
  "password_hash": "zahashowane_haslo"
}
```

**Odpowiedzi:**
- `201 Created` – Użytkownik utworzony
- `400 Bad Request` – Brakuje wymaganych pól
- `409 Conflict` – Użytkownik o takim loginie lub mailu już istnieje

---

### ✅ POST `/auth/login` – Logowanie

**Opis**: Logowanie użytkownika. Hasło także musi być zahashowane po stronie klienta.

**Treść żądania (JSON):**
```json
{
  "username": "użytkownik",
  "password_hash": "zahashowane_haslo"
}
```

**Odpowiedzi:**
- `200 OK` – Zwraca token JWT i ID użytkownika
- `400 Bad Request` – Brakuje loginu lub hasła
- `401 Unauthorized` – Nieprawidłowe dane logowania

---

### ✅ POST `/auth/logout` – Wylogowanie

**Opis**: Pseudo-wylogowanie – po stronie serwera nic się nie dzieje. Klient musi usunąć token JWT.

**Nagłówek wymagany:**
```
Authorization: Bearer <JWT>
```

**Odpowiedź:**
- `200 OK` – Token należy usunąć po stronie klienta

---

## ✅ Endpointy zadań (`/tasks`)

Wszystkie poniższe endpointy wymagają autoryzacji (nagłówek `Authorization: Bearer <JWT>`).

---

### 🔸 GET `/tasks/` – Pobierz wszystkie zadania

**Opis**: Zwraca wszystkie zadania zalogowanego użytkownika.

**Odpowiedź:**
- `200 OK` – Lista zadań (`id`, `title`, `description`, `is_completed`)

---

### 🔸 POST `/tasks/` – Utwórz zadanie

**Treść żądania (JSON):**
```json
{
  "title": "Nowe zadanie",
  "description": "Opis (opcjonalny)"
}
```

**Odpowiedzi:**
- `201 Created` – Zadanie utworzone (zwraca `task_id`)
- `400 Bad Request` – Brakuje tytułu zadania

---

### 🔸 PUT `/tasks/<task_id>` – Edytuj zadanie

**Treść żądania (JSON):**
```json
{
  "title": "Nowy tytuł",
  "description": "Nowy opis",
  "is_completed": true
}
```

**Odpowiedzi:**
- `200 OK` – Zadanie zaktualizowane
- `404 Not Found` – Zadanie nie istnieje lub nie należy do użytkownika

---

### 🔸 DELETE `/tasks/<task_id>` – Usuń zadanie

**Odpowiedzi:**
- `200 OK` – Zadanie usunięte
- `404 Not Found` – Zadanie nie istnieje lub nie należy do użytkownika

---

## 🧾 Wymagane nagłówki (dla PUT/POST/DELETE z JWT)

```
Authorization: Bearer <JWT>
Content-Type: application/json
```

---

## 📌 Uwagi dodatkowe

- Hasła muszą być **haszowane po stronie klienta** (np. SHA256).
- Brak tzw. „prawdziwego wylogowania” – JWT działa bezstanowo, więc wylogowanie polega na **usunięciu tokena przez klienta**.
- Wszystkie odpowiedzi mają format:
```json
{
  "success": true/false,
  "message": "opis",
  "status_code": kod HTTP,
  "data": [...],       // (opcjonalnie)
  "task_id": 1         // (jeśli dotyczy)
}
```
