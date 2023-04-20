<template>
      <div class ="card">
        <img :src="property.images[0]" :alt="property.title" class="property-image" />
        <h2 class="property-title">{{ property.propertyType }}</h2>
        <p class="property-details">
          {{ property.streetNumber }} {{property.apartmentUnit}} {{ property.streetName }}, {{ property.city }}, {{ property.stateAbbreviation }} {{ property.zip }}<br />
          {{ property.bedrooms }} Beds | {{ property.bathrooms }} Baths | {{
            property.squareFootage
          }} sqft
        </p>
        <p class="property-price">Rent: ${{ property.rent }}/month</p>
        <button @click="goToPropertyDetails" class="view-details-btn">View Details
        </button>
      </div>
   
</template>

<script>
export default {
  name: 'property-card',
  props: {property:Object},
  
methods: {
    goToPropertyDetails() {
      this.$router.push({ name: 'property-details', params: { propertyId: this.property.propertyId } });
    }
  }
};
</script>
<style>
* {
  margin: 0;
  box-sizing: border-box;
}

 .home {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem 1rem;
} 

.page-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 2rem;
  color: rgb(255, 255, 255);
} 
.property-card:hover {
  box-shadow:    10px rgba(0, 0, 0, 0.2);
}

.property-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.property-title {
  font-size: 1.25rem;
  margin: 1rem 0;
  text-transform: capitalize;
}

</style>

