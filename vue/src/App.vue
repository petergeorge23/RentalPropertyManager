<template>
  <div id="app">
    <header>
      <nav class="nav">
         <img class="image1" :src="logoImage" alt="Shucky Ducky Estates Logo">
      
        <router-link class="nav-link" to="/">Home</router-link>
        <router-link class="nav-link" to="/properties">Properties</router-link>

        <div v-if="this.$store.state.token">
          <router-link v-if="this.$store.state.user.authorities[0].name === 'ROLE_RENTER'" class="nav-link" to="/portal/renter">Renter Portal</router-link>
          <router-link v-if="this.$store.state.user.authorities[0].name === 'ROLE_LANDLORD'" class="nav-link" to="/portal/landlord">Landlord Portal</router-link>
          <router-link v-if="this.$store.state.user.authorities[0].name === 'ROLE_EMPLOYEE'" class="nav-link" to="/portal/employee">Employee Portal</router-link>
          <router-link class="nav-link" to="/logout">Logout</router-link>
        </div>

        <router-link v-else class="nav-link" to="/login">Login</router-link>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
    <footer>
      <nav class="footer-nav">
        <router-link class="footer-link" to="/about">About Us</router-link>
        <router-link class="footer-link" to="/contact">Contact Us</router-link>
        <p class="copyright">© 2023 Shucky Ducky. All rights reserved.</p>
      </nav>
    </footer>
  </div>
</template>

<script>
import logoImage from '@/assets/pngwing.com.png';
export default {
data() {
    return {
      logoImage
    }
}
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}
main {
  width: 100%;
  overflow: auto;
}

#app {
  display: grid;
  grid-template-rows: auto 1fr auto;
  min-height: 100vh;
}

header {
  margin-bottom: 1rem;
  
}

.nav {
  display: flex;
  justify-content: space-around;
  padding: 1rem;
  background-color:#192841;
  color: #FFFFFF;
}

.nav-link {
  text-decoration: none;
  color: #FFFFFF;
  transition: all 0.3s ease;
  padding: 0.5rem;
  border-radius: 8px;
}

.nav-link:hover {
  background-color: rgb(161, 160, 160);
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

footer {
  margin-top: 10px;
}

.footer-nav {
  display: flex;
  justify-content: flex-start;
  padding: 1rem;
  background-color: #192841;
  
}

.footer-link {
  text-decoration: none;
  color: #333;
  transition: all 0.3s ease;
  padding: 0.5rem;
  margin-right: 1rem;
  border-radius: 8px;
  color:#FFFFFF;
}

.footer-link:hover {
  background-color: rgb(161, 160, 160);
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.copyright {
  margin-left: auto;
  font-size: 0.85rem;
  color: #FFFFFF;
}
 .image1 {
  height: 60px; 
 width: 100px;
 margin-left: 0px;
}
</style>
