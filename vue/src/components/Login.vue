<template>
  <div id="login">
    <form @submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="register-button">Sign in</button>
      <button type="register-button" @click="$router.push({ name: 'register' })">Need an account? Sign up.</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

.form-input-group {
  margin-bottom: 1rem;
}
label {
  color: #FFFFFF;
}
input[type="text"],
input[type="password"] {
  width: 30%;
}
h1 {
  text-align: center;
  color: #FFFFFF;
}
p {
  color: #FFFFFF;
}
form {
  margin-left: 1rem;
}
button {
  margin: 10px;
  margin-left: 0rem;
}
.register-button {
  background-color: #FFFFFF;
  color: #000000;
  border: none;
  padding: 10px;
  cursor: pointer;
}
</style>