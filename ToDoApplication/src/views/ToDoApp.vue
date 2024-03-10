
<style>
  @import url('../assets/ToDoApp.css');
</style>
 
<template>
  <div id="app" class="container">
    <h1 class="text-center">TODO LIST</h1>

    <div class="row mb-2">
      <div class="col">
        <div class="input-group">
          <input type="text" v-model="newTodo" @keyup.enter="addTodo" class="form-control" placeholder="Enter new task">
          <button @click="addTodo" @keyup.enter="addTodo" class="btn btn-primary">Add Task</button>
        </div>
      </div>
    </div>

    <div class="row justify-content-center">
      <div class="col-md-6">
        <h3 class="text-center">OPEN TASKS <span class="badge bg-primary">{{ totalOpenTasks }}</span></h3>

        <div v-if="openTodos.length === 0" class="alert alert-info text-center" role="alert">
          No open tasks
        </div>

        <div>
          <table v-if="openTodos.length > 0" class="table table-bordered table-striped table-hover text-center">
            <!-- Table headers -->
            <thead>
              <tr>
                <th class="text-center fw-bold" style="width: 25%;">CREATED AT</th>
                <th class="text-center fw-bold" style="width: 50%;">TITLE</th>
                <th class="text-center fw-bold" style="width: 25%;">ACTIONS</th>
              </tr>
            </thead>
            <!-- Table body -->
            <tbody>
              <tr v-for="todo in openTodos" :key="todo.id">
                <td class="text-start" style="vertical-align: middle;">
                  <span v-if="editingTodoId !== todo.id" class="badge bg-dark rounded-pill">{{ formatTime(todo.createdAt) }}</span>
                  <span v-else class="badge bg-dark rounded-pill"> {{ formatTime(todo.createdAt) }}</span>
                </td>
                <td style="vertical-align: middle;">
                  <span v-if="editingTodoId !== todo.id">{{ todo.title }}</span>
                  <div v-else>
                    <input type="text" v-model="updatedTodo" class="form-control" @blur="updateTodo(todo)" @keyup.enter="updateTodo(todo)">
                  </div>
                </td>
                <td class="text-end" style="vertical-align: middle;">
                  <div class="d-flex justify-content-end">
                    <button @click="completeTodo(todo)" class="btn btn-success btn-sm me-2">Complete</button>
                    <button @click="editTodo(todo)" class="btn btn-warning btn-sm">Edit</button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <p v-else></p>
        </div>
      </div>


      <div class="col-md-6">
        <h3 class="text-center">COMPLETED TASKS <span class="badge bg-success">{{ totalCompletedTasks }}</span></h3>

        <div v-if="completedTodos.length === 0" class="alert alert-info text-center" role="alert">
          No completed tasks
        </div>

        <table v-else class="table table-bordered table-striped table-hover text-center ">
          <thead>
            <tr>
              <th class="text-center fw-bold" style="width: 25%;">COMPLETED AT</th>
              <th class="text-center fw-bold" style="width: 50%;">TITLE</th>
              <th class="text-center fw-bold" style="width: 25%;">ACTIONS</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="todo in completedTodos" :key="todo.id">
              <td class="text-start" style="vertical-align: middle;">
                <div class="d-flex justify-content-start">
                  <span class="badge bg-dark rounded-pill">{{ formatTime(todo.completedAt) }}</span>
                </div>
              </td>
              <td style="vertical-align: middle;">{{ todo.title }}</td>
              <td class="text-end" style="vertical-align: middle;">
                <div class="d-flex justify-content-end">
                  <button @click="reopenTodo(todo)" class="btn btn-info btn-sm me-2">Reopen</button>
                  <button @click="deleteTodo(todo)" class="btn btn-danger btn-sm">Delete</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <footer class="footer mt-auto py-3 bg-light text-center">
      <div class="container">
        <span class="text-muted">Designed and developed by ASHUTOSH MAURYA </span>
      </div>
    </footer>
  </div>
</template>

<script>
import { fetchTodos } from "../services/fetchTodos";
import { addTodoMixin } from "../services/addTodo";
import { completeTodoMixin } from "../services/completeTodo";
import { deleteTodoMixin } from "../services/deleteTodo";
import { reopenTodoMixin } from "../services/reopenTodo";
import { updateTodoMixin } from '../services/updateTodo';

export default {
  name: "App",
  mixins: [fetchTodos, addTodoMixin, completeTodoMixin, deleteTodoMixin, reopenTodoMixin, updateTodoMixin],
  data() {
    return {
      todos: [],
      newTodo: "",
      updatedTodo: "",
      editingTodoId: null
    };
  },
  computed: {
    openTodos() {
      return this.todos.filter(todo => !todo.completed);
    },
    completedTodos() {
      return this.todos.filter(todo => todo.completed);
    },
    totalOpenTasks() {
      return this.openTodos.length;
    },
    totalCompletedTasks() {
      return this.completedTodos.length;
    }
  },
  created() {
    this.fetchTodos(); // Call fetchTodos method when the component is created
  },
  methods: {
    // formatTime method goes here
    formatTime(timestamp) {
      if (!timestamp) return '';
      return new Date(timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', day: 'numeric', month: 'numeric', year: 'numeric' });
    },
  }
};

</script>
