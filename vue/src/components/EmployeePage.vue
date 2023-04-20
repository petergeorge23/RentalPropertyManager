<!-- EmployeePortal.vue -->
<template>
  <div>
    <h1>Employee Portal</h1>
    <table>
        <thead>
          <tr>
            <th>Maintenance ID</th>
            <th>Property ID</th>
            <th>Renter ID</th>
            <th>Status</th>
            <th>Details</th>
            <th>Notes</th>
          </tr>
        </thead>
        <tbody v-for="maintenance in myMaintenance" v-bind:key="maintenance.maintenanceId" v-bind:maintenance="maintenance">
          <tr>
            <td>{{ maintenance.maintenanceId }}</td>
            <td>{{ maintenance.propertyId }}</td>
            <td>{{ maintenance.renterId }}</td>
            <td>{{ maintenance.maintenanceStatus }}</td>
            <td>{{ maintenance.requestDetails }}</td>
            <td>{{ maintenance.notes }}</td>
            <td><button @click="markComplete(maintenance.maintenanceId)">Mark Complete</button></td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
import maintenanceService from '../services/MaintenanceService.js'

export default {
  computed: {
    myMaintenance() {
      return this.$store.state.myMaintenance;
    }
  },
  created() {
    this.getMyMaintenance()
  },
  methods: {
    getMyMaintenance() {
      maintenanceService.getEmployeeMaintenance().then(response => {
        this.$store.commit("SET_MY_MAINTENANCE", response.data);
      })
    },
    markComplete(id) {
      maintenanceService.markComplete(id).then(response => {
        if (response === 200) {
          alert('The assignment has been marked complete');
          this.$router.push("/portal/employee");
        }
      }).catch (error => {
          if (error.response) {
              this.errorMsg = "Error completing request. Response received was '" + error.response.statusText + "'.";
            } else if (error.request) {
              this.errorMsg = "Error completing request. Server could not be reached.";
            } else {
              this.errorMsg = "Error completing request. Request could not be created.";
            }
        });
    }
  }
};
</script>

<style scoped>
h1 {
  font-size: 2rem;
  margin-bottom: 2rem;
  text-align: center;
  color: #FFFFFF;
}

table {
  width: 100%;
  border-collapse: collapse;
  color:#FFFFFF
}

th, td {
  padding: 1rem;
  border: 1px solid #ccc;
  text-align: left;
}

button {
  background-color: #494949;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

button:hover {
  background-color: #2980b9;
}
</style>
