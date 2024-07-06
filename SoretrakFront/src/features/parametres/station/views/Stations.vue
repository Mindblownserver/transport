<template>
    <mgl-map 
        v-if="center"
        :mapStyle="mapStyle"
        :zoom="zoom"
        :center="center">
        <mgl-navigation-control position="top-left"/>
    </mgl-map>
</template>

<script>
import { MglMap, MglNavigationControl } from 'vue-maplibre-gl'
import maplibregl from 'maplibre-gl'

export default {
  name: 'App',
  components: {
    MglMap,
    MglNavigationControl
  },
  data() {
    return {
      mapStyle: "https://api.maptiler.com/maps/streets/style.json?key=iEA5nD2zUFH2DzzdYBT0",
      zoom: 14,
      center: null  // Initial center is null, to be updated by geolocation
    }
  },
  mounted() {
    // Load the RTL plugin
    maplibregl.setRTLTextPlugin(
      'https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js',
      null,
      true // Lazy load the plugin
    );
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = [position.coords.longitude, position.coords.latitude];
      }, error => {
        console.error("Error getting geolocation: ", error);
        // Fallback to a default location if needed
        this.center = [16.62662018, 49.2125578];  // Example default center
      });
    } else {
      console.error("Geolocation is not supported by this browser.");
      // Fallback to a default location if needed
      this.center = [16.62662018, 49.2125578];  // Example default center
    }
  }
}
</script>

<style lang="scss">
@import "~vue-maplibre-gl/src/css/maplibre.scss";

body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

code {
  font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New', monospace;
}

#app {
  width: 100%;
  height: 100vh;
}
</style>
