import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/property"
});

export default {
    propertyList() {
        return http.get();
    },
    landlordProperties(id) {
      return http.get(`/landlord/${id}`);
    },
    addProperty(property) {
      return http.post('/landlord/add', property);
    },
    deleteProperty(id) {
      return http.delete(`/landlord/remove/${id}`);
    },
    updateProperty(property){
      return http.put('/landlord/update', property);
    },
    assignRenter(assignment) {
      return http.post("/landlord/renter/assign", assignment);
    }
}