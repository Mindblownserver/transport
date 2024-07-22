<template>
    <mgl-map 
      id="map"
        v-if="center"
        :mapStyle="mapStyle"
        :zoom="zoom"
        :center="center">
        <mgl-marker v-for="(marker, index) in markers" :key="index"
          color="#fb8c00"
          :coordinates="marker.getCoords()">
        </mgl-marker>
    </mgl-map>
</template>


<script>
import { MglMap, MglMarker} from 'vue-maplibre-gl'
import maplibregl from 'maplibre-gl'

export default {
  name: 'MapLibre',
  components: {
    MglMap,
    MglMarker,
  },
  data() {
    return {
      mapStyle: "https://api.maptiler.com/maps/streets/style.json?key=iEA5nD2zUFH2DzzdYBT0",
      zoom: 10,
    }
  },
  props:{
    markers:{
      type:Array,
      default:null
    }
  },
  mounted() {
    if(maplibregl.getRTLTextPluginStatus==='unavailable'){
      maplibregl.setRTLTextPlugin(
        'https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js',
        null,
        true // Lazy load the plugin
      );
    }
    console.log(this.markers)

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

#app{
  width:100%;
  height:100vh;
}

#map-container {
  font-family: 'KacstPoster', "DejaVuSansMono-Bold"; /* Use a font that supports Arabic */
  direction: rtl; /* Ensure text direction is right-to-left */
}
</style>