package models

case class TodoListItem(id: Long, description: String, isItDone: Boolean)
case class NewTodoListItem(description: String)
