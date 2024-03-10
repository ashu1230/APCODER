// fetchTodosMixin.js
import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export const fetchTodos = {
  methods: {
    async fetchTodos() {
      try {
        const response = await axios.get("http://localhost:3000/todos");
        this.todos = response.data;
        if (this.todos.length > 0) {
          toast.info("Tasks Available", { autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT,});
        } else {
          toast.error("No Tasks Available", { autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT,});
        }
      } catch (error) {
        console.error("Error fetching todos:", error);
        toast.error("Tasks Not Fetched", { autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT, });
      }
    }
  }
};
