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
                :value="getLignes"
                paginator :rows="4"
                tableStyle="min-width: 50rem"
                :stripedRows="true"
                v-model:expandedRows="expandedRows"
                v-model:filters="filters"
                filterDisplay="row"
                dataKey="idLigne"
                @rowExpand="onRowExpand"
                @rowCollapse="onRowCollapse"
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
                <Column>
                  <template #body="slotProps">
                    <DialogButton @click="openDialog(slotProps.data)" icon="pi pi-search"></DialogButton>
                  </template>
                </Column>
                <template #expansion="slotProps">
                  <div class="p-4">
                    <h5>Itineraries for {{ slotProps.data.nomLigne }}</h5>
                    <DataTable :value="slotProps.data.itinList">
                      <Column field="deDates" header="Date" sortable></Column>
                      <Column field="deDureeTr" header="Duration" sortable></Column>
                      <Column field="deEscale" header="Stopover" sortable></Column>
                      <Column field="deKmsTa" header="Kilometers" sortable></Column>
                      <Column field="deNumLg" header="Line Number" sortable></Column>
                      <Column field="deNumLi" header="Itinerary Number" sortable></Column>
                      <Column field="deTarif" header="Tariff" sortable></Column>
                      <Column field="station" header="Station" sortable></Column>
                    </DataTable>
                  </div>
                </template>
              </DataTable>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <MapLibre v-if="mapVisible" :center="center" :markers="markers" :routes="routes" style="width: 100%; height: 500px;" ref="map"/>
      </div>
    </div>
  </div>
</template>
<script>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import { FilterMatchMode } from '@primevue/core/api';
import PrimeButton from 'primevue/button';
import MapLibre from '../components/Map.vue';  // Adjust the import path as needed
import { MyMarker } from '../repo/MyMarker';  // Adjust the import path as needed

export default {
  name: "Lignes",
  components: {
    DataTable,
    Column,
    InputText,
    DialogButton: PrimeButton,
    MapLibre,
  },
  data() {
    return {
      columns: [
        { field: 'idLigne', header: 'Id Ligne' },
        { field: 'nomLigne', header: 'Ligne FR' },
        { field: 'nomLa', header: 'Ligne AR' },
      ],
      filters: {
        idLigne: { value: null, matchMode: FilterMatchMode.EQUALS },
        nomLigne: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        nomLa: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
      },
      selectedRow: null,
      expandedRows: null,
      mapVisible: false,
      center: [0, 0],  // Default center to avoid issues with uninitialized center
      markers: [],
      routes: [],  // Initialize routes as an empty array
    };
  },
  computed: {
    getLignes() {
      return this.$store.state.ligneModule.lignes;
    },
  },
  mounted() {
    this.$store.dispatch('ligneModule/getLignes');
  },
  methods: {
    async openDialog(ligneData) {
      if (!ligneData || !ligneData.itinList) {
        console.error('Itinerary data is missing');
        return;
      }

      const stationIds = ligneData.itinList.map(item => item.station);
      if (stationIds.length === 0) {
        console.error('No station IDs found');
        return;
      }

      try {
        const fetchStations = stationIds.map(stationId =>
          fetch(`http://localhost:8081/api/Stati/${stationId}`).then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
          })
        );

        const statiData = await Promise.all(fetchStations);

        console.log('Fetched station data:', statiData);

        // Fetch shape data for each ligne
        const shapeResponse = await fetch(`http://localhost:8081/api/shaps/${ligneData.idLigne}`);
        if (!shapeResponse.ok) throw new Error('Network response was not ok');
        const shapeData = await shapeResponse.json();
        console.log('Fetched shape data:', shapeData);

        // Set map center and markers
        if (statiData.length > 0) {
          this.center = [statiData[0].longetude, statiData[0].latitude]; // Center map on the first station
        }
        this.markers = statiData.map(stati => new MyMarker(stati.latitude, stati.longetude, stati.nomFr));

      // Pass shape data as routes
        if (shapeData) {
          this.routes = [shapeData];  
          console.log('Routes data set:', this.routes);
        }


        this.mapVisible = true;
      } catch (error) {
        console.error('Fetch error:', error);
      }
    },
  },
};
</script>