import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export const updateTodoMixin = {
  methods: {
    editTodo(todo) {
      this.editingTodoId = todo.id;
      this.showCreatedAt = false;
      this.updatedTodo = todo.title;
      toast.warning("Edit Your Task", {autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT,});
    },
    async updateTodo(todo) {
      try {
        const response = await axios.put(
          `http://localhost:3000/todos/${todo.id}`,
          {
            ...todo,
            title: this.updatedTodo.trim()
          }
        );
        const index = this.todos.findIndex(t => t.id === todo.id);
        this.todos.splice(index, 1, response.data);
        this.editingTodoId = null;
        toast.success("Task Updated", {autoClose: 2000, theme: 'colored' , position: toast.POSITION.BOTTOM_RIGHT,});
      } catch (error) {
        console.error("Error updating todo:", error);
        toast.error("Updating ToDo", {autoClose: 2000, theme: 'colored' , position: toast.POSITION.BOTTOM_RIGHT,});
      }
    }
  }
};