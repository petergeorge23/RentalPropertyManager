<template>
  <div class="body">
    <h1 class="page-title">Shucky Ducky Estates</h1>
      
     
    <h2>Featured Properties</h2>

    <div class="properties-container1" >
      <property-card v-for="property in mostExpensivest" v-bind:key="property.propertyId" v-bind:property="property"/>
    </div>
  </div>
</template>

<script>

import PropertyCard from './PropertyCard'
import propertyService from '../services/PropertyService.js'
export default {
  name: 'Properties',
  components: {PropertyCard},

  computed: {
    allProperties(){
      return this.$store.state.properties
    },
    mostExpensivest(){

     const properties = this.$store.state.properties;

     const mostExpensivest = properties.sort((a,b)=> b.rent-a.rent).slice(0,3);

     return mostExpensivest;
    },
  },
  methods: {
    getProperties() {
      propertyService.propertyList().then(response => {
        this.$store.commit("SET_PROPERTIES", response.data);
      });
    }
  },
  created() {
    this.getProperties();
  }
};

</script>
<style>
* {
  margin: 0;
  box-sizing: border-box;
}
body {
  background-image: url('https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/cape-elizabeth-lighthouse-portland-head-light-in-maine-gregory-ballos.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
.properties-container1 {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
  flex-direction: row;
}
h2 {
  text-align: center;
  font-family: 'Bungee Shade', cursive;
  font-size: 25px;
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

</style>

