<template>
    <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3">
              <h6 class="text-white text-capitalize ps-3">Tableau des centres</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <DataTable    
              paginator :rows="10" 
              :value="getVehicules" size="large" 
              :stripedRows="true" 
              tableStyle="min-width: 50rem;" 
              v-model:filters="filters" filterDisplay="row"
              selectionMode="single"
              :loading="getLoading"
              v-model:selection="selectedRow">
                  <template #empty> Aucune vehicule n'est trouvée </template>
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
  </div>
</template>

<script>
// create custom http class that has axios set up
// make its methods something along the lines of "getCentres", "getTrips", ...etc
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import { FilterMatchMode } from '@primevue/core/api';

export default {
    name: "Centres",
    components:{
      DataTable,
      Column,
      InputText
    },
    data(){
      return{
        columns: [
          { field: 'decodvh', header: 'Id Vehicule' },
          { field: 'dematri', header: 'Matricue' },
          { field: 'decatvh.decateg', header: 'Type FR' },
          { field: 'decatvh.deacate', header: 'Type AR' },
          { field: 'deccent', header: 'Id centre' },
          { field: 'decdeleg', header: 'Id Deleg' },
        ],
        filters: {
          decodvh: { value: null, matchMode: FilterMatchMode.EQUALS },
          dematri: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          'decatvh.decateg': { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          'decatvh.deacate': { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          deccent: { value: null, matchMode: FilterMatchMode.EQUALS },
          decdeleg: { value: null, matchMode: FilterMatchMode.EQUALS },
        },
        selectedRow:null
      }
    },
    computed:{
      getVehicules(){
        return this.$store.state.vehiculeModule.vehicules;
      },
      getLoading(){
        return this.$store.state.vehiculeModule.loading;
      }
    },
    mounted(){
       this.$store.dispatch("vehiculeModule/getVehicule")
    }
}
</script>
