<template>
    <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3">
              <h6 class="text-white text-capitalize ps-3">Tableau des centres</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <DataTable :value="products" size="large" :stripedRows="true" tableStyle="min-width: 50rem;" v-model:filters="filters" filterDisplay="row">
                  <template #empty> No customers found. </template>
                  <template #loading> Loading customers data. Please wait. </template>
                  <Column v-for="col of columns" sortable :key="col.field" :field="col.field" :header="col.header">
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
    <div class="row">
      <div class="col-12">
        <div class="card my-4">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div
              class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3"
            >
              <h6 class="text-white text-capitalize ps-3">Projects table</h6>
            </div>
          </div>
          <div class="card-body px-0 pb-2">
            <div class="table-responsive p-0">
              <table
                class="table align-items-center justify-content-center mb-0"
              >
                <thead>
                  <tr>
                    <th
                      class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                    >
                      Project
                    </th>
                    <th
                      class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                    >
                      Budget
                    </th>
                    <th
                      class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                    >
                      Status
                    </th>
                    <th
                      class="text-uppercase text-secondary text-xxs font-weight-bolder text-center opacity-7 ps-2"
                    >
                      Completion
                    </th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/logo-asana.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="spotify"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Asana</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$2,500</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">working</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">60%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-info"
                              role="progressbar"
                              aria-valuenow="60"
                              aria-valuemin="0"
                              aria-valuemax="100"
                              style="width: 60%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button class="btn btn-link text-secondary mb-0">
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/github.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="invision"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Github</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$5,000</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">done</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">100%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-success"
                              role="progressbar"
                              aria-valuenow="100"
                              aria-valuemin="0"
                              aria-valuemax="100"
                              style="width: 100%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button
                        class="btn btn-link text-secondary mb-0"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/logo-atlassian.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="jira"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Atlassian</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$3,400</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">canceled</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">30%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-danger"
                              role="progressbar"
                              aria-valuenow="30"
                              aria-valuemin="0"
                              aria-valuemax="30"
                              style="width: 30%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button
                        class="btn btn-link text-secondary mb-0"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/bootstrap.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="webdev"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Bootstrap</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$14,000</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">working</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">80%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-info"
                              role="progressbar"
                              aria-valuenow="80"
                              aria-valuemin="0"
                              aria-valuemax="80"
                              style="width: 80%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button
                        class="btn btn-link text-secondary mb-0"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/logo-slack.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="slack"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Slack</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$1,000</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">canceled</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">0%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-success"
                              role="progressbar"
                              aria-valuenow="0"
                              aria-valuemin="0"
                              aria-valuemax="0"
                              style="width: 0%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button
                        class="btn btn-link text-secondary mb-0"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div class="d-flex px-2">
                        <div>
                          <img
                            src="../../../../assets/images/small-logos/devto.svg"
                            class="avatar avatar-sm rounded-circle me-2"
                            alt="xd"
                          />
                        </div>
                        <div class="my-auto">
                          <h6 class="mb-0 text-sm">Devto</h6>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="text-sm font-weight-bold mb-0">$2,300</p>
                    </td>
                    <td>
                      <span class="text-xs font-weight-bold">done</span>
                    </td>
                    <td class="align-middle text-center">
                      <div
                        class="d-flex align-items-center justify-content-center"
                      >
                        <span class="me-2 text-xs font-weight-bold">100%</span>
                        <div>
                          <div class="progress">
                            <div
                              class="progress-bar bg-gradient-success"
                              role="progressbar"
                              aria-valuenow="100"
                              aria-valuemin="0"
                              aria-valuemax="100"
                              style="width: 100%"
                            ></div>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle">
                      <button
                        class="btn btn-link text-secondary mb-0"
                        aria-haspopup="true"
                        aria-expanded="false"
                      >
                        <i
                          class="fa fa-ellipsis-v text-xs"
                          aria-hidden="true"
                        ></i>
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
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
        products:[
          {
              "id": "4",
              "code": "a230fh0g3",
              "name": "Bamboo Watch",
              "category": "Accessories",
              "quantity": 24,
          },
          {
              "id": "5",
              "code": "b230fh0g3",
              "name": "Bamboo Watch",
              "category": "Accessories",
              "quantity": 7,
          },
          {
              "id": "4",
              "code": "c230fh0g3",
              "name": "Bamboo Watch",
              "category": "Accessories",
              "quantity": 124,
          },
          {
              "id": "4",
              "code": "d230fh0g3",
              "name": "Bamboo Watch",
              "category": "Accessories",
              "quantity": 4,
          },  
        ],
        columns: [
            { field: 'code', header: 'Code' },
            { field: 'name', header: 'Name' },
            { field: 'category', header: 'Category' },
            { field: 'quantity', header: 'Quantity' }
        ],
        filters: {
          code: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          name: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          category: { value: null, matchMode: FilterMatchMode.IN },
          quantity: { value: null, matchMode: FilterMatchMode.EQUALS }
        },
      }
    }
}
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