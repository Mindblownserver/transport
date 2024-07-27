<template>
  <div class="map-wrap">
    <div class="map" ref="mapContainer"></div>
  </div>
</template>

<script setup>
import { Map, setRTLTextPlugin,getRTLTextPluginStatus, Marker,Popup } from 'maplibre-gl';
import { shallowRef, onUnmounted, markRaw } from 'vue';

const mapContainer = shallowRef(null);
const map = shallowRef(null);

function setupMap(markersList){
  let mapWidget = new Map({
    container: mapContainer.value,
    style:  "https://api.maptiler.com/maps/streets/style.json?key=iEA5nD2zUFH2DzzdYBT0",
    center: [10.1063907, 35.6757932],
    zoom: 14,
  });

  // add RTL support
  if(getRTLTextPluginStatus() == "unavailable"){
    setRTLTextPlugin('https://unpkg.com/@mapbox/mapbox-gl-rtl-text@0.2.3/mapbox-gl-rtl-text.js', false);
  }


  markersList.forEach(marker=>{
    
    new Marker({
      color: "#fb8c00",
      draggable: false
    }).setLngLat(marker.getCoords())
    .setPopup(new Popup().setHTML(`<p>Station ${marker.getStatId()}:</p><p style="color:#fb8c00"><b>${marker.getStatNom()}</b></p>`))
    .addTo(mapWidget);
  })
  // append map.value to the actual map widget
  map.value = markRaw(mapWidget);

}

onUnmounted(() => {
  map.value?.remove();
})
defineExpose({
  setupMap
})
</script>


<style >
@import '~maplibre-gl/dist/maplibre-gl.css';

.map-wrap {
  position: relative;
  width: 100%;
  height: 100%; /* Adjust as needed */
}

.map {
  position: absolute;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
}

</style>