<template>
  <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3">
              <h6 class="text-white text-capitalize ps-3">Tableau des Lignes</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <DataTable
                :value="getLignes" :loading="getLoading"
                v-model:expandedRows="expandedRows"
                v-model="selectedRow"
                v-model:filters="filters" filterDisplay="row"
                paginator :rows="9" :stripedRows="true"
                selectionMode="single"
                tableStyle="min-width: 50rem"
                dataKey="idLigne"
                @rowExpand="onRowExpand"
                @rowCollapse="onRowCollapse"
                @rowSelect="onRowSelect" @rowUnselect="onRowUnselect"
              >
                <template #empty> Aucun ligne trouvé </template>
                <template #loading> Veuillez patienter </template>
                <Column expander style="width: 5rem" />
                <Column
                  v-for="col of columns"
                  :key="col.field"
                  :field="col.field"
                  :header="col.header"
                  sortable
                >
                  <template #filter="{ filterModel, filterCallback }">
                    <InputText
                      v-model="filterModel.value"
                      type="text"
                      @input="filterCallback()"
                      placeholder="Search"
                    />
                  </template>
                </Column>
                <template #expansion="slotProps">
                  <div class="p-4">
                    <h5>Itineraries for {{ slotProps.data.nomLigne }}</h5>
                    <DataTable :value="slotProps.data.itinList">
                      <Column field="id.deNumLg" header="id Itin" sortable></Column>
                      <Column field="dates" header="Date" sortable></Column>
                      <Column field="dureetr" header="Duration" sortable></Column>
                      <Column field="escale" header="Stopover" sortable></Column>
                      <Column field="kmsTa" header="Kilometers" sortable></Column>
                      <Column field="tarif" header="Tarif" sortable></Column>
                      <Column field="stat.nomFr" header="Station" sortable></Column>
                    </DataTable>
                  </div>
                </template>
              </DataTable>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row" >
      <div class="col-12">
        <MapLibre id="map" style="width: 100%; height: 500px;" ref="mapWidget"/>
      </div>
    </div>
  </div>
</template>
<script>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import { FilterMatchMode } from '@primevue/core/api';
import MapLibre from '../components/Map.vue';  // Adjust the import path as needed
import { MyMarker } from '../repo/Marker';  // Adjust the import path as needed

export default {
  name: "Lignes",
  components: {
    DataTable,
    Column,
    InputText,
    MapLibre,
  },
  data() {
    return {
      columns: [
        { field: 'idLigne', header: 'Id Ligne'},
        { field: 'nomLigne', header: 'Ligne FR' },
        { field: 'denomla', header: 'Ligne AR' },
        { field: 'type.fr_tpli', header: 'Type Ligne FR' },
        { field: 'type.ar_tpli', header: 'Type Ligne AR' },
        { field: 'deleg.decdeleg', header: 'Id deleg' },
        { field: 'centre.deccentre', header: 'Id Centre' },
      ],
      filters: {
        idLigne: { value: null, matchMode: FilterMatchMode.EQUALS },
        nomLigne: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        denomla: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        'type.ar_tpli': { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        'type.fr_tpli': { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        'deleg.decdeleg': { value: null, matchMode: FilterMatchMode.EQUALS },
        'centre.deccentre': { value: null, matchMode: FilterMatchMode.EQUALS },
      },
      selectedRow: null,
      expandedRows: null,
      markers: [],
      shapList:[],
      center:[],
    };
  },
  computed: {
    getLignes() {
      return this.$store.state.ligneModule.lignes;
    },
    getLoading(){
      return this.$store.state.ligneModule.loading;
    }
  },
  mounted() {
    this.$store.dispatch('ligneModule/getLignes');
  },
  methods: {  
    onRowSelect(ligneData){
      const stations = ligneData.data.itinList.map(item => item.stat);
      let lineGeoJson;
      if (stations.length === 0) {
        console.error('No station IDs found');
        return;
      }

      this.center = [stations[0].longetude, stations[0].latitude];

      this.markers = stations.map(stati => new MyMarker(stati.latitude, stati.longetude,stati.decStat, stati.nomFr));

      // setting up shap
      this.shapList = ligneData.data.shapList;
      if(this.shapList.length>0){
        lineGeoJson = {
          'type': 'geojson',
          'data': {
              'type': 'Feature',
              'properties': {},
              'geometry': {
                  'type': 'LineString',
                  'coordinates': this.shapList.map(shap=>[shap.lon, shap.lat])
              }
          }
        }
      }
      document.getElementById("map").scrollIntoView({
        behavior: 'smooth',
        block:'center'
      })
      this.$refs.mapWidget.setupMap(this.center,this.markers,lineGeoJson);
    }
  },
};
</script>
