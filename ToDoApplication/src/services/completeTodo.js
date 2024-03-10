// completeTodoMixin.js
import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export const completeTodoMixin = {
  methods: {
    async completeTodo(todo) {
      try {
        const response = await axios.put(
          `http://localhost:3000/todos/${todo.id}`,
          {
            ...todo,
            completed: true,
            completedAt: new Date()
          }
        );
        const index = this.todos.findIndex(t => t.id === todo.id);
        this.todos.splice(index, 1, response.data);
        toast.success("Task Completed", {autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT, });
      } catch (error) {
        console.error("Error completing todo:", error);
        toast.error("Error completing todo", {autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT, });
      }
    }
  }
};
