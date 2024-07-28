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

function setupMap(center, markersList, lineGeoJson){
  const finalMarkerIndex = markersList.length-1;
  let mapWidget = new Map({
    container: mapContainer.value,
    style:  "https://api.maptiler.com/maps/streets/style.json?key=iEA5nD2zUFH2DzzdYBT0",
    center: center,
    zoom: 14,
  });

  // add RTL support
  if(getRTLTextPluginStatus() == "unavailable"){
    setRTLTextPlugin('https://unpkg.com/@mapbox/mapbox-gl-rtl-text@0.2.3/mapbox-gl-rtl-text.js', false);
  }


  markersList.forEach((marker, index)=>{
    
    new Marker({
      color: (index==0)?"#03C03C":(index==finalMarkerIndex)?"#CC0000":"#fb8c00",
      draggable: false
    }).setLngLat(marker.getCoords())
    .setPopup(new Popup().setHTML(`<p>Station ${marker.getStatId()}:</p><p style="color:${(index==0)?"#03C03C":(index==finalMarkerIndex)?"#CC0000":"#fb8c00"};font-weight:bold">${marker.getStatNom()}</p>`))
    .addTo(mapWidget);
  })

  // add line layer
  if(lineGeoJson!=undefined){
    mapWidget.on('load', () => {
      mapWidget.addSource('route', lineGeoJson);
      mapWidget.addLayer({
              'id': 'route',
              'type': 'line',
              'source': 'route',
              'layout': {
                  'line-join': 'round',
                  'line-cap': 'round'
              },
              'paint': {
                  'line-color': '#17B169',
                  'line-width': 5
              }
          });
    });
  }
  
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