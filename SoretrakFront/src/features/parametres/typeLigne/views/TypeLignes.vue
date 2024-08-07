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
              <DataTable 
                v-if="!error && !loading"
                :value="getTypeLigne" 
                size="large" 
                :stripedRows="true" 
                tableStyle="min-width: 50rem;" 
                v-model:filters="filters" 
                filterDisplay="row"
                selectionMode="single"
                v-model:selection="selectedRow">
                <template #empty> Aucun type de ligne trouvé </template>
                <template #loading> Veuillez patienter </template>
                <Column 
                  v-for="col in columns" 
                  :key="col.field" 
                  :field="col.field"  
                  :header="col.header" 
                  sortable>
                  <template #filter="{ filterModel, filterCallback }">
                    <InputText 
                      v-model="filterModel.value" 
                      type="text" 
                      @input="filterCallback()" 
                      placeholder="Search by name" />
                  </template>
                </Column>
              </DataTable>
              <div v-else-if="loading" class="text-center">Veuillez patienter...</div>
              <div v-else class="text-center text-danger">{{ errorMessage }}</div>
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
  components: {
    DataTable,
    Column,
    InputText,
  },
  data() {
    return {
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
      selectedRow: null,
      loading: true,
      error: false,
      errorMessage: 'Une erreur s\'est produite lors de la récupération des données.',
    };
  },
  computed: {
    getTypeLigne() {
      return this.$store.state.typeLigneModule.typeLignes;
    },
  },
  async mounted() {
    try {
      await this.$store.dispatch("typeLigneModule/getTypeLigne");
      this.loading = false;
    } catch (error) {
      this.loading = false;
      this.error = true;
    }
  },
};
</script>
