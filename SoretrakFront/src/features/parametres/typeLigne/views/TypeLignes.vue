<template>
    <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3">
              <h6 class="text-white text-capitalize ps-3">Tableau des Types des lignes</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <!-- paginator :rows="2"  -->
              <DataTable 
              :value="getTypeLigne" size="large" 
              :stripedRows="true" 
              tableStyle="min-width: 50rem;" 
              v-model:filters="filters" filterDisplay="row"
              selectionMode="single"
              :loading="getLoading"
              v-model:selection="selectedRow">
                  <template #empty> Aucun deleg  trouvé </template>
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
    name: "TypeLignes",
    components:{
      DataTable,
      Column,
      InputText
    },
    data(){
      return{
        deleg:[],
        columns: [
          { field: 'dec_tpli', header: 'Id Type Ligne' },
          { field: 'ar_tpli', header: 'Type Ligne AR' },
          { field: 'fr_tpli', header: 'Type Ligne FR' },
        ],
        filters: {
            dec_tpli: { value: null, matchMode: FilterMatchMode.EQUALS },
            ar_tpli: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
            fr_tpli: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        },
        selectedRow:null
      }
    },
    computed:{
        getTypeLigne(){
          return this.$store.state.typeLigneModule.typeLignes;
        },
        getLoading(){
          return this.$store.state.typeLigneModule.loading;
        }
    },
    mounted(){
      this.$store.dispatch("typeLigneModule/getTypeLigne");
    }
}
</script>