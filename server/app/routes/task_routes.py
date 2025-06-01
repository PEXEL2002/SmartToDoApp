from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt_identity
from app.models.task_model import Task
from app import db

bp = Blueprint('task_routes', __name__, url_prefix='/todo/api/tasks')

@bp.route('/', methods=['GET'])
@jwt_required()
def get_tasks():
    user_id = get_jwt_identity()
    tasks = Task.query.filter_by(user_id=user_id).all()
    return jsonify({
        "status": 200,
        "tasks": [{
            "id": task.id,
            "title": task.title,
            "description": task.description,
            "is_completed": task.is_completed
        } for task in tasks]
    }), 200

@bp.route('/', methods=['POST'])
@jwt_required()
def create_task():
    user_id = get_jwt_identity()
    data = request.get_json()
    title = data.get("title")
    description = data.get("description", "")

    if not title:
        return jsonify({"status": 400, "msg": "Title is required"}), 400

    task = Task(title=title, description=description, user_id=user_id)
    db.session.add(task)
    db.session.commit()

    return jsonify({"status": 201, "msg": "Task created successfully"}), 201

@bp.route('/<int:task_id>', methods=['PUT'])
@jwt_required()
def update_task(task_id):
    user_id = get_jwt_identity()
    task = Task.query.filter_by(id=task_id, user_id=user_id).first()

    if not task:
        return jsonify({"status": 404, "msg": "Task not found"}), 404

    data = request.get_json()
    task.title = data.get("title", task.title)
    task.description = data.get("description", task.description)
    task.is_completed = data.get("is_completed", task.is_completed)
    db.session.commit()

    return jsonify({"status": 200, "msg": "Task updated successfully"}), 200

@bp.route('/<int:task_id>', methods=['DELETE'])
@jwt_required()
def delete_task(task_id):
    user_id = get_jwt_identity()
    task = Task.query.filter_by(id=task_id, user_id=user_id).first()

    if not task:
        return jsonify({"status": 404, "msg": "Task not found"}), 404

    db.session.delete(task)
    db.session.commit()

    return jsonify({"status": 200, "msg": "Task deleted successfully"}), 200
