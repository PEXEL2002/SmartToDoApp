def verify_password(client_hash: str, stored_hash: str) -> bool:
    """
    Porównuje hash przesłany przez klienta z tym zapisanym w bazie danych.
    Zakłada, że hasło zostało wcześniej zhashowane (np. SHA-256) po stronie klienta.
    """
    return client_hash == stored_hash
