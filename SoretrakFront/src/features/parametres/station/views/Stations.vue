<template>
  <div class="container-fluid py-4">
    
    <Tabs value="0">
      <TabList>
          <Tab value="0">Tableaux</Tab>
          <Tab value="1">Carte</Tab>
      </TabList>
      <TabPanels>
        <TabPanel value="0">
          <br>
          <div class="row">
            <div class="col-12">
              <div class="card my-4">
                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                  <div class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize ps-3">Tableau des stations</h6>
                  </div>
                </div>
                <div class="card-body px-0 pb-2">
                  <div class="table-responsive p-0">
                    <DataTable
                    paginator :rows="10" :loading="getLoading"
                    :value="getStations" size="large" 
                    :stripedRows="true" 
                    tableStyle="min-width: 50rem;" 
                    v-model:filters="filters" filterDisplay="row"
                    selectionMode="single"
                    v-model:selection="selectedRow"
                    @rowSelect="onRowSelect" @rowUnselect="onRowUnselect"
                    >
                        <template #empty> Aucune station trouvée </template>
                        <template #loading> Veuillez patienter </template>
                        <Column v-for="col of columns" sortable :key="col.field" :field="col.field"  :header="col.header">
                          <template #filter="{ filterModel, filterCallback }">
                            <InputText v-model="filterModel.value" type="text" @input="filterCallback()" placeholder="Search by name" />
                          </template>
                        </Column>
                    </DataTable>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
        </TabPanel>
        <TabPanel value="1">
          <br>
          <div class="row">
            <div class="col-12">
              <div class="card my-4">
                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                  <div
                    class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3"
                  >
                    <h6 class="text-white text-capitalize ps-3">Map</h6>
                  </div>
                </div>
                <div class="card-body px-0 pb-2">
                  <div class="table-responsive p-0" style="height: 80vh;">
                    <MapLibre ref="mapWidgetRef" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </TabPanel>
      </TabPanels>
    </Tabs>
</div>
</template>
<script setup>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import { FilterMatchMode } from '@primevue/core/api';
import MapLibre from "../components/Map.vue";
import { MyMarker } from "../repo/Marker.js";

import Tabs from 'primevue/tabs';
import TabList from 'primevue/tablist';
import Tab from 'primevue/tab';
import TabPanels from 'primevue/tabpanels';
import TabPanel from 'primevue/tabpanel';
import { computed, onMounted, ref } from 'vue';
import {useStore} from "vuex"

const columns = ref([
  { field: 'decStat', header: 'Id Station' },
  { field: 'rayon', header: 'Rayon' },
  { field: 'date', header: 'Date' },
  { field: 'nomFr', header: 'Nom FR' },
  { field: 'nomAr', header: 'Nom AR' },
  { field: 'typeSt', header: 'Type' },
  { field: 'latitude', header: 'latitude' },
  { field: 'longetude', header: 'longetude' },
])

const filters = ref({
  decStat: { value: null, matchMode: FilterMatchMode.EQUALS },
  rayon: { value: null, matchMode: FilterMatchMode.EQUALS },
  date: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  nomFr: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  nomAr: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  typeSt: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  latitude: { value: null, matchMode: FilterMatchMode.EQUALS },
  longetude: { value: null, matchMode: FilterMatchMode.EQUALS },
  
})
const selectedRow = ref(null);
const markers = ref([])
const mapWidgetRef = ref(null);

const getLoading= computed(()=>store.state.stationModule.loading)
const getStations = computed(()=>store.state.stationModule.stations)
const store = useStore();

function onRowSelect(e){
  selectedRow.value= e.data
  console.log(selectedRow)
}

function onRowUnselect(e){
  selectedRow.value= e.data
  console.log(selectedRow)
}

function initMarkers(){
  const stations =getStations.value;
  stations.forEach(station => {
    markers.value.push(new MyMarker(station.longetude,station.latitude,station.decStat, station.nomFr));
  });
}

onMounted(()=>{
  store.dispatch("stationModule/getStation").then(()=>{
    initMarkers();
    mapWidgetRef.value.setupMap(markers.value)

  });
})
/* export default {

  mounted(){
    this.$store.dispatch("stationModule/getStation");
  },
  methods:{
    function onRowSelect(e){
      this.selectedRow= e.data
      console.log(this.selectedRow)
    },
    function onRowUnselect(e){
      this.selectedRow= e.data
      console.log(this.selectedRow)
    },
    function getStations(){
      const stations =this.$store.state.stationModule.stations; 
      stations.forEach(station => {
          this.markers.push(new MyMarker(station.longetude,station.latitude,station.decStat));
      });
      return stations;
    },
  }
}
 */
 </script>