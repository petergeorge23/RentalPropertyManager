<template>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
       <div class="form-input-group">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" v-model="user.firstName" required />
      </div>
       <div class="form-input-group">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" v-model="user.lastName" required />
      </div>
       <div class="form-input-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="user.email" required />
      </div>
       <div class="form-input-group">
        <label for="phoneNumber">Phone Number</label>
        <input type="tel" id="phoneNumber" v-model="user.phoneNumber" />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
       <div class="form-input-group">
        <label for="role">Role</label>
        <select type="role" id="role" v-model="user.role" required >
          <option value= "RENTER">Renter</option>
          <option value= "LANDLORD">Landlord</option>
          <option value= "EMPLOYEE">Employee</option>
        </select>
      </div>
      <button type="register-button">Create Account</button>
          <button type="register-button" @click="$router.push({ name: 'login' })">Already have an account? Log in.</button>        
    </form>
  </div>
</template>
<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        lastName: '',
        firstName: '',
        email: '',
        phone: '',
        confirmPassword: '',
        role: '',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>
<style scoped>
#register{
   display: flex;
  justify-content: center;
}
.form-input-group {
  margin-bottom: 1rem;
  color: #FFFFFF;
  
}
h1 {
  text-align: center;
  color: #FFFFFF;
  padding: 10px;
}

form{
  margin-left: 1rem;
  width: 20%;
  text-align: center;
}
button{
  margin: 5px;
  margin-left: 0rem;
  color: #FFFFFF;
  
}
</style>