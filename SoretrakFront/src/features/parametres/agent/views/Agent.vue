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
              :value="getAgents" size="large" 
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

import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import { FilterMatchMode } from '@primevue/core/api';

export default {
    name: "Agents",
    components:{
      DataTable,
      Column,
      InputText
    },
    data(){
      return{
        columns: [
          { field: 'decagen', header: 'Id Agent' },
          { field: 'denagea', header: 'Nom et prenom Ar' },
          { field: 'denagen', header: 'Nom et Prenom Fr' },
          { field: 'decdeleg', header: 'deleg Id' },
         
        ],
        filters: {
            decagen: { value: null, matchMode: FilterMatchMode.EQUALS },
            denagea: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
            denagen: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
            decdeleg: { value: null, matchMode: FilterMatchMode.EQUALS },
        },
        selectedRow:null
      }
    },
    computed:{
      getAgents(){
        return this.$store.state.agentModule.agents;
      },
      getLoading(){
        return this.$store.state.agentModule.loading;
      }
    },
    mounted(){
       this.$store.dispatch("agentModule/getAgents")
    }
}
</script>
