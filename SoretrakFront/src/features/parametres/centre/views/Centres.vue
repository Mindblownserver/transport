<template>
  <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3">
              <h6 class="text-white text-capitalize ps-3">Projects table</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <DataTable :value="products" size="large" :stripedRows="true" tableStyle="min-width: 50rem;" v-model:filters="filters" filterDisplay="row">
                <template #empty> No customers found. </template>
                <template #loading> Loading customers data. Please wait. </template>
                <Column v-for="col of columns" sortable :key="col.field" :field="col.field" :header="col.header">
                  <template #filter="{ filterModel, filterCallback }">
                    <InputText v-model="filterModel.value" type="text" @input="filterCallback()" :placeholder="'Search by ' + col.header.toLowerCase()" />
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
import axiosInstance from '../MyApiInstance'; // Adjust path as per your project

export default {
  ar_centre: "Centres",
  components: {
    DataTable,
    Column,
    InputText
  },
  data() {
    return {
      products: [],
      columns: [
        { field: 'dec_centre', header: 'dec_centre' },
        { field: 'ar_centre', header: 'ar_centre' },
        { field: 'del_centre', header: 'del_centre' },
      ],
      filters: {
        dec_centre: { value: null, matchMode: FilterMatchMode.EQUALS },
        ar_centre: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        del_centre: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
      },
    };
  },
  mounted() {
    this.fetchData(); // Fetch data when the component is mounted
  },
  methods: {
    fetchData() {
      axiosInstance.getCentres()
        .then(response => {
          this.products = response.data; // Assuming response.data is an array of objects
          console.log('Fetched data:', this.products); // Logging fetched data to the console
        })
        .catch(error => {
          console.error('Error fetching centres data', error);
        });
    },
  },
};
</script>

<style >
:root{
  --p-datatable-row-striped-background: #f8fafc;
  --p-datatable-body-cell-border-color: #E2E8F0;
  --p-datatable-header-cell-border-color: #E2E8F0;
  --p-datatable-header-cell-gap: 5px;
  --p-inputtext-color: rgb(161, 161, 161);
  --p-inputtext-background: #ffffff;
  --p-inputtext-padding-y:5px;
  --p-inputtext-padding-x:5px;
  --p-inputtext-border-color: var(--p-datatable-body-cell-border-color);
  --p-inputtext-transition-duration: 1s;
  --p-inputtext-border-radius: 5px;
  --p-datatable-filter-inline-gap:5px
  
}
</style>
