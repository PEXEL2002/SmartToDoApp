from flask import Blueprint, request, jsonify
from app.models.user_model import User
from app.utils.token_utils import generate_token
from app import db
from flask_jwt_extended import jwt_required

bp = Blueprint('auth_routes', __name__, url_prefix='/todo/api/auth')


@bp.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data.get("username")
    email = data.get("email")
    password_hash = data.get("password_hash")  # hashed on client

    if not username or not email or not password_hash:
        return jsonify({"success": False, "message": "Missing required fields.", "status_code":400}), 400

    if User.query.filter((User.username == username) | (User.email == email)).first():
        return jsonify({"success": False, "message": "User already exists.","status_code": 409}), 409

    new_user = User(username=username, email=email, password_hash=password_hash)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({"success": True, "message": "User registered successfully.", "status_code":201}), 201


@bp.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data.get("username")
    password_hash = data.get("password_hash")  # hashed on client

    if not username or not password_hash:
        return jsonify({"success": False, "message": "Missing username or password."}), 400

    user = User.query.filter_by(username=username).first()
    if not user or user.password_hash != password_hash:
        return jsonify({"success": False, "message": "Invalid credentials."}), 401

    token = generate_token(identity=user.id)
    return jsonify({
        "success": True,
        "message": "Login successful.",
        "token": token,
        "user_id": user.id,
        "status_code": 200
    }), 200


@bp.route('/logout', methods=['POST'])
@jwt_required()
def logout():
    return jsonify({
        "success": True,
        "message": "Logged out successfully (client must discard the token).",
        "status_code": 200
    }), 200
