<template>
  <div class="about-us">
    <h1 class="about-title">About Us</h1>
    <div class="card-container">
      <div class="card history">
        <h2>Company History</h2>
        <p>Who the duck are we? Shucky Ducky Estates was founded in 2005 with the goal of providing exceptional property management services to the luxury real estate market. Since then, we have grown to become one of the most reputable and trusted property management companies in the industry.</p>
        <p>Our team is made up of experienced professionals who are dedicated to providing personalized and efficient service to our clients. We are committed to staying up-to-date with the latest trends and technologies in the industry to ensure that we are always providing the best possible service.</p>
        <p>At Shucky Ducky Estates, we are passionate about what we do and we take pride in helping our clients achieve their real estate goals. Whether you are a property owner looking for management services or a tenant searching for your dream home, we are here to help.</p>
      </div>
      <div class="card services">
        <h2>Our Services</h2>
        <div class="service">
          <h3>Find a Rental</h3>
          <p>Our Find a Rental service is designed to help tenants find the perfect home to fit their needs and lifestyle. We offer a wide range of luxury rental properties in the most sought-after locations, and our team is here to help you every step of the way.</p>
        </div>
        <div class="service">
          <h3>Owner Services</h3>
          <p>Our Owner Services are designed to help property owners maximize their investment and minimize their stress. We handle everything from property maintenance and marketing to tenant screening and rent collection, so you can sit back and relax while we take care of everything.</p>
        </div>
        <div class="service">
          <h3>Rental Services</h3>
          <p>Our Rental Services are designed to help tenants and property owners navigate the rental process with ease. We handle everything from lease preparation and move-in inspections to rent collection and maintenance requests, so you can focus on what really matters.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutUs',
};
</script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Bungee+Shade&display=swap');
h1 {
  text-align: center;
  font-family: 'Bungee Shade', cursive;
  font-size: 3rem;
  background-image: linear-gradient(to left, #ffffff, #ffffff, #ffffff, #ffffff);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
  animation: colorchange 5s linear infinite;
}

@keyframes colorchange {
  0% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 100% 50%;
  }
}
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  gap: 20px;
  width: 100%;
}

.card {
  background-color: #FFFFFF;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  padding: 20px;
  width: 45%;
}

.card h2 {
  color: #252525;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}

.card p {
  color: #7F7F7F;
  font-size: 16px;
  line-height: 1.5;
  margin-bottom: 30px;
}

.services {
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
}

.service {
  text-align: center;
}

.service h3 {
  color: #252525;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.service p {
  color: #7F7F7F;
  font-size: 16px;
  line-height: 1.5;
}
</style>
