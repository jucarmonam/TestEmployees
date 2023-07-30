<script setup lang="ts"></script>

<template>
  <main class="home">
    <h1>Type an Employee ID</h1>
    <div class="input-group mb-3">
      <input v-model="ID" type="number" class="form-control" placeholder="Employee's ID" aria-label="Recipient's username" aria-describedby="basic-addon2">
      <div class="input-group-append">
        <button @click="retrieveEmployees" class="btn btn-outline-secondary" type="button">Button</button>
      </div>
    </div>
    <table v-if="employees.length > 0" class="table table-hover">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Name</th>
          <th scope="col">Salary</th>
          <th scope="col">Anual salary</th>
          <th scope="col">Age</th>
          <th scope="col">Image url</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(employee, id) in employees" :key="id">
          <th scope="row">{{ employee.id }}</th>
          <td>{{ employee.employee_name }}</td>
          <td>{{ employee.employee_salary }}</td>
          <td>{{ employee.employee_anual_salary || 0}}</td>
          <td>{{ employee.employee_age }}</td>
          <td>{{ employee.profile_image || 'none'}}</td>
        </tr>
      </tbody>
    </table>
    <div v-else>
        <h2>{{ Message }}</h2>
    </div>
  </main>
</template>

<script lang="ts">
import DataService from "../services/DataService";
// import type
import type Employee from '@/types/Employee'

export default {
  name: "employees-list",
  data() {
    return {
      employees: [] as Employee[],
      ID: "" as number | string,
      Message: "No info to show"
    };
  },
  methods: {
    async retrieveEmployees() {
      if(this.ID != ""){
        try {
          const response = await DataService.get(this.ID as number)
          this.employees = [response.data.data];
          console.log(response.data);
        } catch (e: any) {
          console.log("error: ",e);
          this.employees = [];
          this.Message = e.reponse.data.message;
        }
      }else{
        try {
          const response = await DataService.getAll();
          this.employees = response.data.data;
          console.log(response.data.data);
        } catch (e: any) {
          console.log(e);
          this.employees = [];
          this.Message = e.reponse.data.message;
        }
      }
    }
  }
};
</script>

<style scoped>
.home{
  margin: 0 30%;
}

h1{
  text-align: center;
  color: aquamarine;
}
</style>