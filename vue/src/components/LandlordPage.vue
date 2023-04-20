<template>
  <div>
    <h1>Landlord Portal</h1>
    <h1>Hello, {{this.$store.state.user.firstName}}</h1>
    <div class="container">
      <div class="card" @click="toggleViewProperties" @mouseover="hoverCard" @mouseleave="leaveCard">
        <span>View Properties</span>
      </div>
      <div class="card" @click="toggleAddProperty" @mouseover="hoverCard" @mouseleave="leaveCard">
        <span>Add Property</span>
      </div>
      <div class="card" @click="toggleMaintenanceRequests" @mouseover="hoverCard" @mouseleave="leaveCard">
        <span>View All Maintenance Requests</span>
      </div>
      <div class="card" @click="toggleViewRents" @mouseover="hoverCard" @mouseleave="leaveCard">
        <span>View My Rents</span>
      </div>
    </div>

    <div id="list" v-if="viewAllMaintenanceRequests">
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
            <td>
              <button @click="showAssignEmployeeForm(maintenance)">Assign Employee to Maintenance</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-if="viewAssignEmployee">
      <form @submit.prevent="assignMaintenance(maintenanceAssignment)">
          <label for="maintenanceId">Maintenance ID: </label>
          <input type="number" :disabled="true" id="maintenanceId" v-model="maintenanceAssignment.maintenanceId" required>

          <label for="employeeId">Employee ID: </label>
          <input type="number" id="employeeId" v-model="maintenanceAssignment.employeeId" required>

          <button type="submit">Assign Employee</button>
      </form>
    </div>

    <div id="list" v-if="viewRents">
      <table>
        <thead>
          <tr>
            <th>Rent ID</th>
            <th>Status</th>
            <th>Monthly Rent</th>
            <th>Renter ID</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody v-for="rent in allMyRents" v-bind:key="rent.rentId" v-bind:rent="rent">
          <tr>
            <td>{{ rent.rentId }}</td>
            <td>{{ rent.rentStatus }}</td>
            <td>{{ rent.rentAmount }}</td>
            <td>{{ rent.renterId }}</td>
            <td>{{ rent.balance }}</td>
            <td>
              <button>Charge Rent</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div id="list" v-if="viewPropertiesVisible">
      <table>
        <thead>
          <tr>
            <th>Street</th>
            <th>City</th>
            <th>State</th>
            <th>Zip</th>
            <th>Rent</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody v-for="property in myProperties" v-bind:key="property.propertyId" v-bind:property="property">
          <tr>
            <td>{{ property.streetNumber }} {{ property.streetName }} {{property.apartmentUnit}}</td>
            <td>{{ property.city }}</td>
            <td>{{ property.stateAbbreviation }}</td>
            <td>{{ property.zip }}</td>
            <td>${{ property.rent }}</td>
            <td>
              <button @click="removeProperty(property.propertyId)">Remove Property</button>
              <button @click="showUpdatePropertyForm(property)">Update Property</button>
              <button @click="showAssignRenterForm(property)">Assign Renter to Property</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="viewAssignRenter">
      <form @submit.prevent="assignRenter(renterAssignment)">
        <label for="propertyId">Property ID: </label>
        <input type="number" :disabled="true" id="propertyId" v-model="renterAssignment.propertyId" required>

        <label for="renterId">Renter ID: </label>
        <input type="number" id="employeeId" v-model="renterAssignment.renterId" required>

        <button type="submit">Assign Renter</button>
      </form>
    </div>

    <div v-if="updatePropertyVisible">
      <form  @submit.prevent="updateProperty">
        <label for="propertyId">PropertyId: </label>
        <input type="number" :disabled="true" id="propertyId" v-model="updatedProperty.propertyId" required>

        <label for="rent">Rent: </label>
        <input type="number" id="rent" v-model="updatedProperty.rent" required>

        <label for="dateAvailable">Date Available: </label>
        <input type="date" id="dateAvailable" v-model="updatedProperty.dateAvailable" required>

        <label for="propertyType">Property Type: </label>
        <select id="propertyType" v-model="updatedProperty.propertyType">
          <option>house</option>
          <option>apartment</option>
          <option>condo</option>
        </select>
                    
        <label for="streetNumber">Street Number: </label>
        <input type="text" id="streetNumber" v-model="updatedProperty.streetNumber" required>

        <label for="apartmentUnit">Apartment / Unit: </label>
        <input type="text" id="apartmentUnit" v-model="updatedProperty.apartmentUnit">

        <label for="streetName">Street Name: </label>
        <input type="text" id="streetName" v-model="updatedProperty.streetName" required>

        <label for="city">City: </label>
        <input type="text" id="city" v-model="updatedProperty.city" required>

        <label for="State">State: </label>
        <input type="text" id="state" v-model="updatedProperty.stateAbbreviation" required>

        <label for="zip">Zip: </label>
        <input type="number" id="zip" v-model="updatedProperty.zip" required>

        <label for="bathrooms">Bathrooms: </label>
        <input type="number" id="bathrooms" v-model="updatedProperty.bathrooms" required>

        <label for="bedrooms">Bedrooms: </label>
        <input type="number" id="bedrooms" v-model="updatedProperty.bedrooms" required>

        <label for="squareFootage">Square Footage: </label>
        <input type="number" id="squareFootage" v-model="updatedProperty.squareFootage" required>

        <label for="petsAllowed">Pets Allowed: </label>
        <input type="checkbox" id="petsAllowed" v-model="updatedProperty.petsAllowed" required>
                  
        <label for="propertyDescription">Description: </label>
        <textarea name="propertyDescription" id="propertyDescription" v-model="updatedProperty.propertyDescription"></textarea>

        <button type="submit">Update Property</button>
      </form>
    </div>
    <div v-if="addPropertyVisible">
      <form @submit.prevent="addProperty">
        <label for="rent">Rent: </label>
        <input type="number" id="rent" v-model="newProperty.rent" required>

        <label for="dateAvailable">Date Available: </label>
        <input type="date" id="dateAvailable" v-model="newProperty.dateAvailable" required>

        <label for="propertyType">Property Type: </label>
          <select id="propertyType" v-model="newProperty.propertyType">
            <option>house</option>
            <option>apartment</option>
            <option>condo</option>
          </select>
          
        <label for="streetNumber">Street Number: </label>
        <input type="text" id="streetNumber" v-model="newProperty.streetNumber" required>

        <label for="apartmentUnit">Apartment / Unit: </label>
        <input type="text" id="apartmentUnit" v-model="newProperty.apartmentUnit">

        <label for="streetName">Street Name: </label>
        <input type="text" id="streetName" v-model="newProperty.streetName" required>

        <label for="city">City: </label>
        <input type="text" id="city" v-model="newProperty.city" required>

        <label for="State">State: </label>
        <input type="text" id="state" v-model="newProperty.stateAbbreviation" required>

        <label for="zip">Zip: </label>
        <input type="number" id="zip" v-model="newProperty.zip" required>

        <label for="bathrooms">Bathrooms: </label>
        <input type="number" id="bathrooms" v-model="newProperty.bathrooms" required>

        <label for="bedrooms">Bedrooms: </label>
        <input type="number" id="bedrooms" v-model="newProperty.bedrooms" required>

        <label for="squareFootage">Square Footage: </label>
        <input type="number" id="squareFootage" v-model="newProperty.squareFootage" required>

        <label for="petsAllowed">Pets Allowed: </label>
        <input type="checkbox" id="petsAllowed" v-model="newProperty.petsAllowed" required>
        
        <label for="propertyDescription">Description: </label>
        <textarea name="propertyDescription" id="propertyDescription" v-model="newProperty.propertyDescription"></textarea>

        <button type="submit">Add Property</button>
      </form>
    </div>        
  </div>
</template>

<script>
import propertyService from '../services/PropertyService.js'
import maintenanceService from '../services/MaintenanceService.js'
import rentService from '../services/RentService.js'

export default {
  data() {
    return {
      viewPropertiesVisible: false,
      addPropertyVisible: false,
      updatePropertyVisible: false,
      viewAllMaintenanceRequests: false,
      viewRents: false,
      viewAssignRenter: false,
      viewAssignEmployee: false,
      newProperty: {
        rent: 0,
        propertyType: '',
        bathrooms: 0, 
        bedrooms: 0,
        squareFootage: 0,
        dateAvailable: '',
        streetNumber: '',
        streetName: '',
        apartmentUnit: '',
        city: '',
        stateAbbreviation: '',
        zip: 0,
        petsAllowed: true,
        propertyDescription: "",
        amenities: [],
        images: []
      },
      updatedProperty: {
        rent: 0,
        propertyType: '',
        bathrooms: 0, 
        bedrooms: 0,
        squareFootage: 0,
        dateAvailable: '',
        streetNumber: '',
        streetName: '',
        apartmentUnit: '',
        city: '',
        stateAbbreviation: '',
        zip: 0,
        petsAllowed: true,
        propertyDescription: "",
        amenities: [],
        images: []
      },
      maintenanceAssignment: {
        maintenanceId: 0,
        employeeId: 0
      },
      renterAssignment: {
        propertyId: 0,
        renterId: 0
      }
    }
  },
  computed: {
    myProperties() {
      return this.$store.state.landlordProperties;
    },
    myMaintenance() {
      return this.$store.state.myMaintenance;
    },
    allMyRents() {
      return this.$store.state.landlordRents;
    }
  },
  created() {
      this.getMyProperties();
      this.getMyMaintenance();
      this.getLandlordRents();
  },
  methods: {
    showUpdatePropertyForm(property) {
      this.updatePropertyVisible = !this.updatePropertyVisible;
      this.addPropertyVisible = false;
      this.viewPropertiesVisible = false;
      this.viewRents = false;
      Object.assign(this.updatedProperty, property);
    },
    getMyProperties() {
      const id = this.$store.state.user.id;
      propertyService.landlordProperties(id).then(response => {
        this.$store.commit("SET_LANDLORD_PROPERTIES", response.data);
      })
    },
    getMyMaintenance() {
      maintenanceService.getMyMaintenance().then(response => {
        this.$store.commit("SET_MY_MAINTENANCE", response.data);
      })
    },
    getLandlordRents() {
      rentService.landlordRents().then(response => {
        this.$store.commit("SET_LANDLORD_RENTS", response.data);
      })
    },
    showAssignEmployeeForm(maintenance) {
      this.viewAssignEmployee = !this.viewAssignEmployee;
      this.viewPropertiesVisible = false;
      this.addPropertyVisible = false;
      this.viewRents = false;
      this.viewAssignRenter = false;
      this.maintenanceAssignment.maintenanceId = maintenance.maintenanceId;
    },
    showAssignRenterForm(property) {
      this.viewAssignRenter = !this.viewAssignRenter;
      this.viewAssignEmployee = false;
      this.addPropertyVisible = false;
      this.viewRents = false;
      this.viewAllMaintenanceRequests = false;
      this.renterAssignment.propertyId = property.propertyId;
    },
    toggleViewRents() {
      this.viewRents = !this.viewRents;
      this.viewPropertiesVisible = false;
      this.addPropertyVisible = false;
      this.viewAllMaintenanceRequests =false;
      this.viewAssignRenter = false;
      this.viewAssignEmployee = false;
    },
    toggleViewProperties() {
      this.viewPropertiesVisible = !this.viewPropertiesVisible;
      this.addPropertyVisible = false;
      this.viewAllMaintenanceRequests =false;
      this.viewRents = false;
      this.viewAssignRenter = false;
      this.viewAssignEmployee = false;
    },
    toggleAddProperty() {
      this.addPropertyVisible = !this.addPropertyVisible;
      this.viewPropertiesVisible = false;
      this.viewAllMaintenanceRequests=false;
      this.viewRents = false;
      this.viewAssignRenter = false;
      this.viewAssignEmployee = false;
    },
    toggleMaintenanceRequests() {
      this.viewAllMaintenanceRequests = !this.viewAllMaintenanceRequests;
      this.viewPropertiesVisible = false;
      this.addPropertyVisible =false;
      this.viewRents = false;
      this.viewAssignRenter = false;
      this.viewAssignEmployee = false;
    },
    hoverCard(event) {
      event.currentTarget.style.backgroundColor = "#d4d4d4";
      event.currentTarget.style.transform = "translateY(-5px)";
    },
    leaveCard(event) {
      event.currentTarget.style.backgroundColor = "#e9e9e9";
      event.currentTarget.style.transform = "translateY(0)";
    },
    assignMaintenance(maintenanceAssignment) {
      maintenanceService.assignEmployee(maintenanceAssignment).then((response) => {
        if (response.status === 200) {
          this.maintenanceAssignment = {
            maintenanceId: 0,
            employeeId: 0
          }
          this.viewAssignEmployee = false;
          alert("You have assigned the request to an employee");
        }
      }).catch(error => {
          if (error.response) {
              this.errorMsg = "Error assigning employee. Response received was '" + error.response.statusText + "'.";
            } else if (error.request) {
              this.errorMsg = "Error assigning employee. Server could not be reached.";
            } else {
              this.errorMsg = "Error assigning employee. Request could not be created.";
            }
        });
    },
    assignRenter(renterAssignment) {
      propertyService.assignRenter(renterAssignment).then((response) => {
        if (response.status === 200) {
          this.renterAssignment = {
            propertyId: 0,
            renterId: 0
          }
          this.viewAssignRenter = false;
          alert("You have assigned the property to a renter");
        }
      }).catch(error => {
          if (error.response) {
              this.errorMsg = "Error assigning renter. Response received was '" + error.response.statusText + "'.";
            } else if (error.request) {
              this.errorMsg = "Error assigning renter. Server could not be reached.";
            } else {
              this.errorMsg = "Error assigning renter. Request could not be created.";
            }
        });
    },
    removeProperty(id) {
       if (confirm("Are you sure you want to delete this property? Action cannot be undone.")) {
        propertyService.deleteProperty(id).then((response) => {
          if (response.status === 200) {
            alert("Property successfully deleted.");
            this.getMyProperties();
          }
        }).catch(error => {
          if (error.response) {
              this.errorMsg = "Error deleting property. Response received was '" + error.response.statusText + "'.";
            } else if (error.request) {
              this.errorMsg = "Error deleting property. Server could not be reached.";
            } else {
              this.errorMsg = "Error deleting property. Request could not be created.";
            }
        });
      }
    },
    updateProperty() {
      propertyService.updateProperty(this.updatedProperty).then((response)=>{
        if(response.status === 200) {
          this.updatedProperty ={
            propertyId: 0,
            rent: 0,
            propertyType: '',
            bathrooms: 0, 
            bedrooms: 0,
            squareFootage: 0,
            dateAvailable: '',
            streetNumber: '',
            streetName: '',
            apartmentUnit: '',
            city: '',
            stateAbbreviation: '',
            zip: 0,
            petsAllowed: true,
            propertyDescription: "",
            amenities: [],
            images: []
          },
          this.updatePropertyVisible = false;
          alert("Property has been updated");
        }
          }).catch((error) => {
        if (error.response) {
          this.errorMsg = "Error updating property. Reponse received was '" + error.response.statusText + "'.";
        } else if (error.request) {
          this.errorMsg = "Error updating property. Server could not be reached.";
        } else {
          this.errorMsg = "Error updating property. Request could not be created.";
        }
      })
    },
    addProperty() {
      propertyService.addProperty(this.newProperty).then((response) => {
        if (response.status === 201) {
          this.newProperty = {
            rent: 0,
            propertyType: '',
            bathrooms: 0, 
            bedrooms: 0,
            squareFootage: 0,
            dateAvailable: '',
            streetNumber: '',
            streetName: '',
            apartmentUnit: '',
            city: '',
            stateAbbreviation: '',
            zip: 0,
            petsAllowed: true,
            propertyDescription: "",
            amenities: [],
            images: []
          }
          alert("Property has been added");
        }
      }).catch((error) => {
        if (error.response) {
          this.errorMsg = "Error adding property. Reponse received was '" + error.response.statusText + "'.";
        } else if (error.request) {
          this.errorMsg = "Error adding property. Server could not be reached.";
        } else {
          this.errorMsg = "Error adding property. Request could not be created.";
        }
      })
      }
    }
  };

</script>

<style scoped>
li{
  color: #ffffff;
  font-size: 25px;
}
body {
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  
}
.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #ffffff;
  padding: 1rem;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: 0.3s;
  margin: 1rem;
  width: 50%;
  min-height: 150px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
  
}

.card:hover {
  background-color: #141414;
  transform: translateY(-5px);
}

h1 {
  font-size: 3rem;
  margin-bottom: 2rem;
  text-align: center;
  color:#ffffff;
}

form {
  display: flex;
   font-size: 2rem;
  flex-direction: column;
  width: 50%;
  color: #ffffff;
  margin-left: 1rem;
}

label, input, button {
  margin-bottom: 1rem;
}
.register-card{
  width:30%
}
thead {
  color: white;
  font-size: 1rem;
  margin: 20px;
  margin-right: 20px;
}
tr{
  color: white;
  font-size: 2rem;
  margin-right: 20px;
}
</style>
