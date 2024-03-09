<!-- 
<template>
  <div id="app" class="container">
    <h1 class="text-center mb-4">TODO LIST</h1>
    <div class="row mt-4">
      <div class="col">
        <div class="input-group">
          <input type="text" v-model="newTodo" @keyup.enter="addTodo" class="form-control" placeholder="Enter new task">
          <button @click="addTodo" @keyup.enter="addTodo" class="btn btn-primary">Add Task</button>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <h3>Open Tasks</h3>
        <div v-if="openTodos.length === 0" class="alert alert-info" role="alert">
          No open tasks
        </div>
        <ul class="list-group" v-else>
          <li v-for="todo in openTodos" :key="todo.id" class="list-group-item d-flex justify-content-between align-items-center">
            <span v-if="editingTodoId !== todo.id" class="badge bg-secondary rounded-pill">{{ formatTime(todo.createdAt) }}</span>
            <span v-else class="badge bg-secondary rounded-pill"> {{ formatTime(todo.createdAt) }}</span>
            <span v-if="editingTodoId !== todo.id">{{ todo.title }}</span>
            <div v-else>
              <input type="text" v-model="updatedTodo" class="form-control" @blur="updateTodo(todo)" @keyup.enter="updateTodo(todo)">
            </div>
            <div>
              <button @click="completeTodo(todo)" class="btn btn-success btn-sm me-3">Complete</button>
              <button @click="editTodo(todo)" class="btn btn-warning   btn-sm">Edit</button>
            </div>
          </li>
        </ul>
      </div>
      <div class="col-md-6">
        <h3>Completed Tasks</h3>
        <div v-if="completedTodos.length === 0" class="alert alert-info" role="alert">
          No completed tasks
        </div>
        <ul class="list-group" v-else>
          <li v-for="todo in completedTodos" :key="todo.id" class="list-group-item d-flex justify-content-between align-items-center">
            <span>{{ todo.title }}</span>
            <span class="badge bg-secondary rounded-pill">{{ formatTime(todo.completedAt) }}</span>
            <div>
              <button @click="reopenTodo(todo)" class="btn btn-info btn-sm me-2">Reopen</button>
              <button @click="deleteTodo(todo)" class="btn btn-danger btn-sm">Delete</button>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      todos: [],
      newTodo: "",
      updatedTodo: "",
      editingTodoId: null
    };
  },
  created() {
    this.fetchTodos();
  },
  computed: {
    openTodos() {
      return this.todos.filter(todo => !todo.completed);
    },
    completedTodos() {
      return this.todos.filter(todo => todo.completed);
    }
  },
  methods: {
    async fetchTodos() {
      try {
        const response = await axios.get("http://localhost:3000/todos");
        this.todos = response.data;
      } catch (error) {
        console.error("Error fetching todos:", error);
      }
    },
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
      } catch (error) {
        console.error("Error adding todo:", error);
      }
    },
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
      } catch (error) {
        console.error("Error completing todo:", error);
      }
    },
    async deleteTodo(todo) {
      const confirmDelete = confirm("Are you sure you want to delete this task?");
      if (!confirmDelete) return; // If user cancels deletion, exit the method
      try {
        await axios.delete(`http://localhost:3000/todos/${todo.id}`);
        this.todos = this.todos.filter(t => t.id !== todo.id);
      } catch (error) {
        console.error("Error deleting todo:", error);
      }
    },

    editTodo(todo) {
      this.editingTodoId = todo.id;
      this.showCreatedAt = false; // Add this line to hide "Created At" badge
      this.updatedTodo = todo.title;
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
      } catch (error) {
        console.error("Error updating todo:", error);
      }
    },
    async reopenTodo(todo) {
      try {
        const response = await axios.put(
          `http://localhost:3000/todos/${todo.id}`,
          {
            ...todo,
            completed: false,
            completedAt: null // Reset completedAt timestamp
          }
        );
        const index = this.todos.findIndex(t => t.id === todo.id);
        this.todos.splice(index, 1, response.data);
      } catch (error) {
        console.error("Error reopening todo:", error);
      }
    },
    formatTime(timestamp) {
      if (!timestamp) return ''; // If timestamp is not available
      return new Date(timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', day: 'numeric', month: 'short', year: 'numeric' });
    },
  }
};
</script>


-->

 
<template>
  <div id="app" class="container">
    <h1 class="text-center mb-4">TODO LIST</h1>
    <div class="row mt-4">
      <div class="col">
        <div class="input-group">
          <input type="text" v-model="newTodo" @keyup.enter="addTodo" class="form-control" placeholder="Enter new task">
          <button @click="addTodo" @keyup.enter="addTodo" class="btn btn-primary">Add Task</button>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <h3>Open Tasks</h3>
        <div v-if="openTodos.length === 0" class="alert alert-info" role="alert">
          No open tasks
        </div>
        <ul class="list-group" v-else>
          <!-- Template -->
          <li v-for="todo in openTodos" :key="todo.id" class="list-group-item d-flex justify-content-between align-items-center">
            <span v-if="editingTodoId !== todo.id" class="badge bg-secondary rounded-pill">{{ formatTime(todo.createdAt) }}</span>
            <span v-else class="badge bg-secondary rounded-pill"> {{ formatTime(todo.createdAt) }}</span>
            <span v-if="editingTodoId !== todo.id">{{ todo.title }}</span>
            <div v-else>
              <input type="text" v-model="updatedTodo" class="form-control" @blur="updateTodo(todo)" @keyup.enter="updateTodo(todo)">
            </div>
            <div>
              <button @click="completeTodo(todo)" class="btn btn-success btn-sm me-3">Complete</button>
              <button @click="editTodo(todo)" class="btn btn-warning   btn-sm">Edit</button>
            </div>
          </li>
        </ul>
      </div>
      <div class="col-md-6">
        <h3>Completed Tasks</h3>
        <div v-if="completedTodos.length === 0" class="alert alert-info" role="alert">
          No completed tasks
        </div>
        <ul class="list-group" v-else>
          <!-- Template -->
          <li v-for="todo in completedTodos" :key="todo.id" class="list-group-item d-flex justify-content-between align-items-center">
            <span>{{ todo.title }}</span>
            <span class="badge bg-secondary rounded-pill">{{ formatTime(todo.completedAt) }}</span>
            <div>
              <button @click="reopenTodo(todo)" class="btn btn-info btn-sm me-2">Reopen</button>
              <button @click="deleteTodo(todo)" class="btn btn-danger btn-sm">Delete</button>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      todos: [],
      newTodo: "",
      updatedTodo: "",
      editingTodoId: null
    };
  },
  created() {
    this.fetchTodos();
  },
  computed: {
    openTodos() {
      return this.todos.filter(todo => !todo.completed);
    },
    completedTodos() {
      return this.todos.filter(todo => todo.completed);
    }
  },
  methods: {
    async fetchTodos() {
      try {
        const response = await axios.get("http://localhost:3000/todos");
        this.todos = response.data;
      } catch (error) {
        console.error("Error fetching todos:", error);
      }
    },
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
      } catch (error) {
        console.error("Error adding todo:", error);
      }
    },
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
      } catch (error) {
        console.error("Error completing todo:", error);
      }
    },
    async deleteTodo(todo) {
      const confirmDelete = confirm("Are you sure you want to delete this task?");
      if (!confirmDelete) return; // If user cancels deletion, exit the method
      try {
        await axios.delete(`http://localhost:3000/todos/${todo.id}`);
        this.todos = this.todos.filter(t => t.id !== todo.id);
      } catch (error) {
        console.error("Error deleting todo:", error);
      }
    },

    editTodo(todo) {
      this.editingTodoId = todo.id;
      this.showCreatedAt = false; // Add this line to hide "Created At" badge
      this.updatedTodo = todo.title;
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
      } catch (error) {
        console.error("Error updating todo:", error);
      }
    },
    async reopenTodo(todo) {
      try {
        const response = await axios.put(
          `http://localhost:3000/todos/${todo.id}`,
          {
            ...todo,
            completed: false,
            completedAt: null // Reset completedAt timestamp
          }
        );
        const index = this.todos.findIndex(t => t.id === todo.id);
        this.todos.splice(index, 1, response.data);
      } catch (error) {
        console.error("Error reopening todo:", error);
      }
    },
    formatTime(timestamp) {
      if (!timestamp) return ''; // If timestamp is not available
      return new Date(timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', day: 'numeric', month: 'short', year: 'numeric' });
    },
  }
};
</script>




