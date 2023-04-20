import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/maintenance"
});

export default {
    sendRequest(maintenance) {
        return http.post('/renter/request', maintenance);
    },
    getMyMaintenance() {
      return http.get('/landlord')
    },
    assignEmployee(assignment) {
      return http.put("/landlord/assign", assignment);
    },
    getEmployeeMaintenance() {
      return http.get('/employee')
    },
    markComplete(id) {
      return http.put(`/employee/${id}/complete`, id);
    }
}