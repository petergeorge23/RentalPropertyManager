<template>
  <div class="description-amenities-card">
    <div class="tabs">
      <button class="tab" @click="showDescription">Description</button>
      <button class="tab" @click="showAmenities">Amenities</button>
    </div>
    <div v-if="descriptionVisible" class="tab-content">
      <p>{{ property.propertyDescription }}</p>
    </div>
    <div v-if="amenitiesVisible" class="tab-content">
      <ul>
        <li v-for="(amenity, index) in property.amenities" :key="index">{{ amenity }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "description-amenities-card",
 computed: {
 property(){
   let currentProperty = this.$store.state.properties.find((aProperty) => {
          return aProperty.propertyId === this.$route.params.propertyId
        })
        if (currentProperty != null){
            return currentProperty;
        } else {
            return null;
        }
        }
    },
data() {
  return {
    descriptionVisible: true,
    amenitiesVisible: false,
  };
},
methods: {
    showDescription() {
      this.descriptionVisible = true;
      this.amenitiesVisible = false;
    },
    showAmenities() {
      this.descriptionVisible = false;
      this.amenitiesVisible = true;
    },
  },
};
</script>
<style scoped>
.description-amenities-card {
  width: 100%;
  max-width: 1000px;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2); 
}

.tabs {
  display: flex;
}

.tab {
  background-color: #f1f1f1;
  border: none;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  cursor: pointer;
  font-size: 1.2rem;
  margin-right: 5px;
  padding: 10px;
}

.tab-content {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}



button {
  color: black;
}
</style>