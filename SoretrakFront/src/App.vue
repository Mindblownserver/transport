<template>
  <sidenav
    :custom_class="getColor"
    :class="[isRTL ? 'fixed-end' : 'fixed-start']"
    v-if="showSidenav"
  />
  <!-- Fix the theming problem. -->
  <main
    class="main-content position-relative max-height-vh-100 h-100 overflow-x-hidden"
  >
    <!-- nav -->
    <navbar
      :class="[isNavFixed ? navbarFixed : '', isAbsolute ? absolute : '']"
      :color="isAbsolute ? 'text-white opacity-8' : ''"
      :minNav="navbarMinimize"
      v-if="showNavbar"
    />
    <router-view />
    <app-footer v-show="showFooter" />
    <configurator
      :toggle="toggleConfigurator"
      :class="[showConfig ? 'show' : '', hideConfigButton ? 'd-none' : '']"
    />
  </main>
</template>
<script>
import Sidenav from "./features/sidenav/components/";
import Configurator from "@/components/Configurator.vue";
import Navbar from "@/components/Navbars/Navbar.vue";
import { mapMutations, mapState } from "vuex";

export default {
  name: "App",
  components: {
    Sidenav,
    Configurator,
    Navbar,
  },
  methods: {
    ...mapMutations(["toggleConfigurator", "navbarMinimize"])
  },
  computed: {
    ...mapState([
      "showSidenav",
      "showNavbar",
      "showFooter",
      "showConfig",
      "hideConfigButton",
    ]),
    ...mapState("sidebar",["isNavFixed", "isAbsolute","navbarFixed","absolute","color"]),
    
  },
  beforeMount() {
    this.$store.state.isTransparent = "bg-transparent";

    const sidenav = document.getElementsByClassName("g-sidenav-show")[0];

    if (window.innerWidth > 1200) {
      sidenav.classList.add("g-sidenav-pinned");
    }
  }
};

</script>

<style>

*{
  scrollbar-width: thin;
  scrollbar-color: rgb(148, 148, 148) rgba(236, 233, 233, 0.882);/**Thumb track */ 
}

:root{
  /* Color schemes */
  --bg-gradient-success: linear-gradient(195deg, #66bb6a 0%, #43a047 100%);
  --hover-color: #F1F5F9;
  --gray-color: rgb(161, 161, 161);
  --soretrak-color: linear-gradient(195deg, #ffa726 0%, #fb8c00 100%);
  --simple-soretrak-color: #fb8c00;
  /* Data Table Style */
  --p-datatable-row-striped-background: #f8fafc;
  --p-datatable-body-cell-border-color: #E2E8F0;
  --p-datatable-header-cell-border-color: #E2E8F0;
  --p-datatable-header-cell-gap: 5px;
  --p-datatable-transition-duration: 0.3s;
  /* Input Text Style */
  --p-inputtext-color: var(--gray-color);
  --p-inputtext-background: #ffffff;
  --p-inputtext-padding-y:5px;
  --p-inputtext-padding-x:5px;
  --p-inputtext-border-color: var(--p-datatable-body-cell-border-color);
  --p-inputtext-transition-duration: 1s;
  --p-inputtext-border-radius: 5px;
  /* DataTable's Filter Style  */
  --p-datatable-filter-inline-gap:5px;
  --p-datatable-filter-constraint-list-padding: 5px;
  /* The usual style of overlay component */
  --p-datatable-filter-overlay-select-background: var(--p-inputtext-background);
  --p-datatable-filter-overlay-select-color: black;
  --p-datatable-filter-overlay-select-border-color: var(--p-datatable-row-striped-background);
  --p-datatable-filter-overlay-select-border-radius: var(--p-inputtext-border-radius);
  --p-datatable-filter-overlay-select-shadow:1px 1px 10px 3px #cacaca93;
  /* The Style of select element */
  --p-datatable-filter-constraint-focus-background: var(--hover-color);
  --p-datatable-filter-constraint-selected-background: var(--p-datatable-filter-constraint-focus-background);
  --p-datatable-filter-constraint-list-gap: 5px;
  --p-datatable-filter-constraint-padding: 5px;
  --p-datatable-filter-constraint-color:black;
  --p-datatable-filter-constraint-border-radius: var(--p-datatable-filter-overlay-select-border-radius);
  /* DataTable selected Style */
  --p-datatable-body-cell-selected-border-color: var(--hover-color);
  --p-datatable-row-selected-background: #dcf9e1/* var(--hover-color) */;
  /* Pagination Style */
  --p-paginator-gap: 10px;
  --p-datatable-paginator-bottom-border-color: transparent;
  --p-paginator-padding:10px;
  --p-paginator-nav-button-selected-background: var(--bg-gradient-success);
  --p-paginator-nav-button-color:var(--gray-color);
  --p-paginator-nav-button-selected-color: white;
  --p-paginator-nav-button-border-radius: 20px;
  --p-paginator-nav-button-height: 2rem;
  --p-paginator-nav-button-width: 2rem;
  --p-paginator-transition-duration: 0.5s;
  /* Tab Style*/
  --p-tabs-tab-active-background:transparent;
  --p-tabs-tab-active-border-color: var(--simple-soretrak-color);
  --p-tabs-tab-active-color: var(--simple-soretrak-color);

  --p-tabs-tab-border-width: 0 0 1px 0;
  --p-tabs-tab-border-color: #f0f2f5;
  --p-tabs-tablist-border-color: #f0f2f5;
  --p-tabs-tablist-border-width: 0 0 1px 0;
  --p-tabs-tab-padding: 1rem 1.125rem;
  --p-tabs-tab-margin:0 0 -1px 0;
  
  --p-tabs-tab-font-weight:600;
  --p-tabs-tablist-background: transparent;
  --p-tabs-tab-color:var(--gray-color);
  --p-tabs-tab-hover-border-color: transparent;
  --p-tabs-transition-duration:0.2s;

  /* Icon postion in Input */
  --p-form-field-padding-x:0.75rem;
  --p-icon-size: 1.2rem;
  --p-iconfield-icon-color: var(--gray-color);

  /* Select Buttons */
/*   --card-background:#fff;
  --card-border: 1px solid #e2e8f0;
  --p-selectbutton-border-radius:10px;
  --p-selectbutton-border-radius:5px;
  --p-togglebutton-checked-background:#f1f5f9;
  --p-togglebutton-checked-border-color:#f1f5f9;
  --p-togglebutton-checked-color:#f1f5f9;
  --p-togglebutton-gap:0.5rem;
  --p-togglebutton-content-checked-background:#ffffff;
  --p-togglebutton-content-checked-shadow:0px 1px 2px 0px rgba(0,0,0,0.02),0px 1px 2px 0px rgba(0,0,0,0.04); 
  --p-togglebutton-transition-duration:0.2s;
  --p-togglebutton-content-left:100px;
  --p-togglebutton-content-top:100px;
  --p-togglebutton-border-radius:5px; */

}
</style>
