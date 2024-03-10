// addTodoMixin.js
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import axios from "axios";

export const addTodoMixin = {
  methods: {
    async addTodo() {
      if (this.newTodo.trim() === "") return;
      try {
        const response = await axios.post("http://localhost:3000/todos", {
          title: this.newTodo.trim(),
          completed: false,
          createdAt: new Date()
        });
        this.todos.push(response.data);
        this.newTodo = "";
        toast.success("New Task Added", {autoClose: 2000, theme: 'colored' , position: toast.POSITION.BOTTOM_RIGHT,});
      } catch (error) {
        console.error("Error adding todo:", error);
        toast.error("New Task Not Added", {autoClose: 2000, theme: 'colored', position: toast.POSITION.BOTTOM_RIGHT,});
      }
    }
  }
};