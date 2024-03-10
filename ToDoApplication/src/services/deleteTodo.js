// deleteTodoMixin.js
import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export const deleteTodoMixin = {
  methods: {
    async deleteTodo(todo) {
      const confirmDelete = confirm("Are you sure you want to delete this task?");
      if (!confirmDelete) return;
      try {
        await axios.delete(`http://localhost:3000/todos/${todo.id}`);
        this.todos = this.todos.filter(t => t.id !== todo.id);
        toast.error("Task Deleted", {autoClose: 1000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT});
      } catch (error) {
        console.error("Error deleting todo:", error);
        toast.error("Someting Wrong", {autoClose: 1000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT});
      }
    }
  }
};
