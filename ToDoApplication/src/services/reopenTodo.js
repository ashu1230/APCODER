// editAndReopenTodoMixin.js
import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export const reopenTodoMixin = {
  methods: {
    async reopenTodo(todo) {
      try {
        const response = await axios.put(
          `http://localhost:3000/todos/${todo.id}`,
          {
            ...todo,
            completed: false,
            completedAt: null
          }
        );
        const index = this.todos.findIndex(t => t.id === todo.id);
        this.todos.splice(index, 1, response.data);
        toast.info("Task Again Opened", {autoClose: 2000, theme:'colored', position: toast.POSITION.BOTTOM_RIGHT,});
      } catch (error) {
        console.error("Error reopening todo:", error);
        toast.error("Error Task Reopen", {autoClose: 2000, position: toast.POSITION.BOTTOM_RIGHT,});
      }
    }
  }
};
