from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_jwt_extended import JWTManager
from config import Config

# Tworzenie instancji rozszerzeń
db = SQLAlchemy()
jwt = JWTManager()

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    jwt.init_app(app)

    from app.routes import auth_routes, task_routes
    app.register_blueprint(auth_routes.bp)
    app.register_blueprint(task_routes.bp)

    return app
