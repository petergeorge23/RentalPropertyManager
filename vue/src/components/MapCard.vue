<template>
  <div class="map-card" v-if="mapCenter && markerPosition">
    <GmapMap :center="mapCenter" :zoom="16" style="width: 100%; height: 100%;">
      <GmapMarker :position="markerPosition"></GmapMarker>
    </GmapMap>
  </div>
</template>

<script>
import { Map as GmapMap, Marker as GmapMarker } from 'vue2-google-maps';

export default {
  components: {
    GmapMap,
    GmapMarker,
  },
  props: ['propertyId'],
  data() {
    return {
      mapCenter: null,
      markerPosition: null,
    };
  },
  computed: {
    property() {
      let currentProperty = this.$store.state.properties.find(
        (aProperty) => {
          return aProperty.propertyId === this.$route.params.propertyId;
        },
      );
      if (currentProperty != null) {
        return currentProperty;
      } else {
        return null;
      }
    },
  },
  mounted() {
    if (this.property) {
      const address = this.getFullAddress(
        this.property.streetNumber,
        this.property.streetName,
        this.property.city,
        this.property.stateAbbreviation,
        this.property.zip,
      );
      this.getLatLngFromAddress(address);
    }
  },
  methods: {
    getFullAddress(streetNumber, streetName, city, stateAbbreviation, zip) {
      return `${streetNumber} ${streetName}, ${city}, ${stateAbbreviation} ${zip}`;
    },
    async getLatLngFromAddress(address) {
      try {
        const response = await fetch(
          `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(
            address,
          )}&key=AIzaSyDex6YvN3GJkOAUe-fX6uZnccQ89g6UWz8`,
        );
        const data = await response.json();
        if (data.status === 'OK') {
          const location = data.results[0].geometry.location;
          this.mapCenter = {
            lat: location.lat,
            lng: location.lng,
          };
          this.markerPosition = {
            lat: location.lat,
            lng: location.lng,
          };
        } else {
          console.error('Error fetching geocoding data:', data.status);
        }
      } catch (error) {
        console.error('Error fetching geocoding data:', error);
      }
    },
  },
};
</script>

<style scoped>
.map-card {
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  padding: 10px;
  display: flex;
  flex-direction: column;
  width: 450px;
  max-width: 1000px;
  height: 550px;
  
  top: 0;
  right: 0;
}
</style>
