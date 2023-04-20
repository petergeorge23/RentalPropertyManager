<template>
  <div class="container">
    <div class="contact-us">
      <h1>Contact Us</h1>
      <div class="company-info">
        <h3>Shucky Ducky Estates</h3>
        <p>123 Main St</p>
        <p>Anytown, USA 12345</p>
        <p>Phone: 555-555-5555</p>
        <p>Fax: 555-555-5556</p>
      </div>
      <div class="contact-form">
        <h3>Drop Us a Line!</h3>
        <form>
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="name" />
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email" />
          </div>
          <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" v-model="phone" />
          </div>
          <div class="form-group">
            <label for="message">Message:</label>
            <textarea id="message" v-model="message"></textarea>
          </div>
          <div class="form-group">
            <button type="register-button">Submit</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContactUs",
  data() {
    return {
      name: "",
      email: "",
      phone: "",
      message: "",
    };
  },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Bungee+Shade&display=swap');
h1 {
  text-align: center;
  font-family: 'Bungee Shade', cursive;
  font-size: 3rem;
  background-image: linear-gradient(to left, #ff6347, #f8b500, #7ed56f, #56ccf2);
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
.container {
  display: flex;
  justify-content: center;
}

.contact-us {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
  color: #FFFFFF;
}

.company-info {
  text-align: center;
}

.contact-form {
  margin-top: 50px;
}


 label {
  display: block;
  margin-bottom: 5px;
} 

input[type="text"],
input[type="email"],
input[type="tel"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

h2 {
  text-align: center;
}

h3 {
  margin-bottom: 20px;
}
</style>