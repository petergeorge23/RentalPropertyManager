<template>
  <div class="overview-card">
     <div class="image-slider">
          <img :src="property.images[imageIndex]" alt="Property Image" />
          <button class="arrow-button left" @click="previousImage">&lt;</button>
          <button class="arrow-button right" @click="nextImage">&gt;</button>
          
        </div>
    <div class="details">
      <h3>{{ property.title }}</h3>
      <p>{{ property.streetNumber }} {{ property.streetName }}, {{ property.city }}, {{ property.state }} {{ property.zip }}</p>
      <div class="info">
        <div class="beds-baths">
          <span>{{ property.bedrooms }} beds</span> &middot;
          <span>{{ property.bathrooms }} baths</span> &middot;
          <span>{{ property.squareFootage }} sq.ft</span>
        </div>
      </div>
      <div class="rent">
        ${{ property.rent}}/month
      </div>
      <div class="amenities">
        <span v-for="(amenity, index) in property.amenities" :key="index">{{ amenity}}</span>
      </div>
      
    </div>
  </div>
</template>

<script>
export default {
  name: 'overview-card',
  data(){
     
      return {
        imageIndex: 0
      }
  },
   computed: {
      property(){
        let currentProperty = this.$store.state.properties.find((aProperty) => {
          return aProperty.propertyId === this.$route.params.propertyId
        })
        if (currentProperty != null){
            return currentProperty;
            }
        else{
            return null;
            }
        },

    
},
methods: {
      previousImage() {
          if(this.imageIndex == 0){
            this.imageIndex = this.property.images.length-1;
          }
          else{
            this.imageIndex--;
        }
          
},
      nextImage() {
           if(this.imageIndex == this.property.images.length-1){
            this.imageIndex = 0;
          }
          else{
            this.imageIndex++;
          }
}
    },
};
</script>

<style scoped>
.overview-card {
  display: flex;
  flex-direction: column;
  background-color: white;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  max-width: 420px;
  overflow: hidden;
  padding: 1rem;
}

.image-container {
  width: 100%;
  height: 200px;
  overflow: hidden;
  position: relative;
}

.image-container img {
  width: 100%;
  height: auto;
  object-fit: cover;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.details {
  padding: 16px;
}

h3 {
  margin: 0;
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin-bottom: 4px;
}

p {
  font-size: 14px;
  color: #777;
  margin: 0;
  margin-bottom: 12px;
}

.info {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #777;
  margin-bottom: 8px;
}

.rent {
  font-size: 18px;
  font-weight: 600;
  color: #333;
  margin-bottom: 8px;
}

.amenities {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
}

.amenities span {
  font-size: 12px;
  color: #777;
  background-color: #f5f5f5;
  padding: 2px 6px;
  border-radius: 3px;
}



</style>