import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/rent"
});

export default {
    myRent() {
        return http.get('/renter');
    },
    landlordRents() {
      return http.get('/landlord')
    }
}