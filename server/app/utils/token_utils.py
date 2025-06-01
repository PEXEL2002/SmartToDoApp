from flask_jwt_extended import create_access_token
from datetime import timedelta

def generate_token(identity: str, expires_in_minutes: int = 60) -> str:
    """
    Generuje token JWT dla danego `identity` (np. ID lub email użytkownika).
    """
    expires = timedelta(minutes=expires_in_minutes)
    return create_access_token(identity=identity, expires_delta=expires)
