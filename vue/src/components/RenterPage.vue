<template>
  <div class="page-container">
    <h1 class="title">Renter Portal</h1>
    <h1>Hello, {{this.$store.state.user.firstName}}</h1>
    <div class="cards-container">
      <div class="card">
        <h2>Rent Due</h2>
        <p>Current Balance: ${{ rent.balance }}</p>
        <button @click="showPaymentForm">Pay Rent</button>
        <div v-show="paymentFormVisible">
          <form>
            <div class="form-group">
              <label for="ccNumber">Credit Card Number:</label>
              <input type="text" id="ccNumber" name="ccNumber" required>
            </div>
            <div class="form-group">
              <label for="expDate">Expiration Date:</label>
              <input type="text" id="expDate" name="expDate" required>
            </div>
            <div class="form-group">
              <label for="cvv">CVV:</label>
              <input type="password" id="cvv" name="cvv" required>
            </div>
            <div class="form-group">
              <input type="submit" value="Pay Now">
            </div>
          </form>
        </div>
      </div>
      <div class="card">
        <h2>Maintenance Request</h2>
        <form v-on:submit.prevent>
          <div class="form-group">
            <label for="request">Enter your maintenance request:</label>
            <textarea id="request" name="request" v-model="maintenance.requestDetails"></textarea>
          </div>
          <div class="form-group">
            <button type="submit" v-on:click="sendRequest()">Send Request</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import rentService from '../services/RentService';
import maintenanceService from '../services/MaintenanceService'

export default {
  data() {
    return {
      paymentFormVisible: false,
      rent: {},
      maintenance: {
        requestDetails: '',
        propertyId: 0
      }
    }
  },
  created() {
    rentService.myRent().then((response => {
      this.rent = response.data;
      this.maintenance.propertyId = response.data.propertyId
    }))
  },
  methods: {
    showPaymentForm() {
      this.paymentFormVisible = !this.paymentFormVisible;
    },
    sendRequest() {
      maintenanceService.sendRequest(this.maintenance).then((response) => {
        if (response.status === 201) {
          this.maintenance = {
            requestDetails: ''
          }
        }
      }).catch((error) => {
        if (error.response) {
          this.errorMsg = "Error submitting maintenance request. Reponse received was '" + error.response.statusText + "'.";
        } else if (error.request) {
          this.errorMsg = "Error submitting maintenance request. Server could not be reached.";
        } else {
          this.errorMsg = "Error submitting maintenance request. Request could not be created.";
        }
      })
    }
  }
}
</script>
<style>
.page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 30px;
}
.title {
  margin-top: 30px;
  margin-bottom: 30px;
  font-size: 36px;
  text-align: center;
  align-content: top;
  color:#FFFFFF;
}
.cards-container {
  display: flex;
  justify-content: space-around;
  width: 100%;
}
.card {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  margin: 0px;
  padding: 30px;
  width: 40%;
  min-width: 300px;
  max-width: 500px;
  font-weight: bold;
  font-size: 26px;
}
.form-group {
  margin-bottom: 10px;
}
label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}
input[type="text"], input[type="password"], input[type="number"], textarea {
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  font-size: 14px;
  padding: 10px;
  width: 100%;
}
input[type="submit"] {
  background-color: #2c3e50;
  border: none;
  border-radius: 3px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 20px;
}
button {
  background-color: #2c3e50;
  border: none;
  border-radius: 3px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 20px;
}
button:focus {
  outline: none;
}
</style>