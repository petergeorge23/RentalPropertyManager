<template>
  <div >
    <h1>Property Details</h1>
   <div class="elements">
     <div class="cards-container">
     
      <overview-card/>
      <map-card/> 
      
      </div>
      <div class="description-container">
      <description-amenities-card />
      </div>
    </div>
  </div>
</template>
<script>

import DescriptionAmenitiesCard from '../components/DescriptionAmenitiesCard.vue'
import OverviewCard from '../components/OverviewCard.vue'
import MapCard from '../components/MapCard.vue'
export default {
 
  components: {
    OverviewCard, 
    MapCard,
    DescriptionAmenitiesCard
    }
}
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

.elements {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: auto auto;
  height: 100vh;
}

.cards-container {
  display: flex;
  justify-content: space-evenly;
  align-items: flex-start;
  padding: 10px;
}

.description-amenities-card {
  width: 100%;
  max-width: 1000px;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  margin-bottom: 0px;
  padding: 30px;
}

.description-container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 10px;
  width: 100%;
}
</style>




