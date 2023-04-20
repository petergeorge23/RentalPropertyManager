<template>
  <div class="body">
    <h1 class="page-title">Properties</h1>
    <div class="properties-container" >
      <property-card v-for="property in allProperties" v-bind:key="property.propertyId" v-bind:property="property"/>
    </div>
  </div>
</template>

<script>
import PropertyCard from './PropertyCard'

export default {
  name: 'Properties',
  components: {
    PropertyCard
  },
  computed: {
    allProperties() {
      return this.$store.state.properties;
    }
  }
};
</script>
<style>
* {
  margin: 0;
  box-sizing: border-box;
}
.properties-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
} 
</style>

