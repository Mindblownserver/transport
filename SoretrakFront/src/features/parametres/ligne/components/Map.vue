<template>
  <div class="map-wrap">
    <a href="https://www.maptiler.com" class="watermark">
      <img src="https://api.maptiler.com/resources/logo.svg" alt="MapTiler logo" />
    </a>
    <div class="map" ref="mapContainer"></div>
  </div>
</template>

<script>
import { Map, NavigationControl, Marker, Popup } from 'maplibre-gl';
import { shallowRef, onMounted, onUnmounted, markRaw, watch } from 'vue';
import maplibregl from 'maplibre-gl';

export default {
  name: "MapComponent",
  props: {
    markers: {
      type: Array,
      default: () => []
    },
    routes: {
      type: Array,
      default: () => []
    }
  },
  setup(props) {
    const mapContainer = shallowRef(null);
    const map = shallowRef(null);
    const mapMarkers = shallowRef([]);

    const drawRoute = (map, routes) => {
      console.log('Drawing route with data:', routes);
      const geojson = {
        type: 'FeatureCollection',
        features: routes.map(route => ({
          type: 'Feature',
          geometry: {
            type: 'LineString',
            coordinates: route.map(coord => [coord.lon, coord.lat])
          }
        }))
      };

      if (map.getSource('route')) {
        map.getSource('route').setData(geojson);
      } else {
        map.addSource('route', {
          type: 'geojson',
          data: geojson
        });

        map.addLayer({
          id: 'route',
          type: 'line',
          source: 'route',
          layout: {
            'line-join': 'round',
            'line-cap': 'round'
          },
          paint: {
            'line-color': '#4899F1',
            'line-width': 5
          }
        });
      }
    };

    const addMarkersToMap = (map, markers) => {
      mapMarkers.value.forEach(marker => marker.remove());
      mapMarkers.value = markers.map(marker => {
        const mapMarker = new Marker({ color: marker.color || "#FF0000" })
          .setLngLat(marker.getCoords())
          .setPopup(new Popup({ offset: 25 }).setHTML(`<p style="color:#fb8c00">Station ${marker.getName()}</p>`))
          .addTo(map);
        return mapMarker;
      });
    };

    onMounted(() => {
      const apiKey = 'iEA5nD2zUFH2DzzdYBT0';
      const { markers, routes } = props;

      const initialCenter = markers.length > 0 ? markers[0].getCoords() : [0, 0];

      map.value = markRaw(new Map({
        container: mapContainer.value,
        style: `https://api.maptiler.com/maps/streets-v2/style.json?key=${apiKey}`,
        center: initialCenter,
        zoom: 13
      }));

      map.value.addControl(new NavigationControl(), 'top-right');

      addMarkersToMap(map.value, markers);

      map.value.on('load', () => {
        drawRoute(map.value, routes);
      });

      if (maplibregl.getRTLTextPluginStatus() === 'unavailable') {
        maplibregl.setRTLTextPlugin(
          'https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js',
          null,
          true // Lazy load the plugin
        );
      }
    });

    onUnmounted(() => {
      map.value?.remove();
    });

    watch(() => [props.markers, props.routes], ([newMarkers, newRoutes]) => {
      if (map.value) {
        addMarkersToMap(map.value, newMarkers);
        drawRoute(map.value, newRoutes);
      }
    }, { deep: true });

    return {
      map, mapContainer
    };
  }
};
</script>

<style scoped>
@import '~maplibre-gl/dist/maplibre-gl.css';

.map-wrap {
  position: relative;
  width: 100%;
  height: calc(100vh - 77px); /* calculate height of the screen minus the heading */
}

.map {
  position: absolute;
  width: 100%;
  height: 100%;
}

.watermark {
  position: absolute;
  left: 10px;
  bottom: 10px;
  z-index: 999;
}

.maplibregl-popup {
  max-width: 200px;
}
</style>
