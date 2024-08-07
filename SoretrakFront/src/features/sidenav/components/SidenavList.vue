<template>
  <div class="w-auto h-auto collapse navbar-collapse max-height-vh-100 h-100" id="sidenav-collapse-main">
    <ul class="navbar-nav">

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/dashboard" navText="Dashboard">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">dashboard</i>
          </template>
        </sidenav-collapse>
      </li>


      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/rtl-page" navText="Rtl">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">format_textdirection_r_to_l</i>
          </template>
        </sidenav-collapse>
      </li>


      <li class="mt-3 nav-item">
        <h6 class="text-xs ps-4 text-uppercase font-weight-bolder text-white"
          :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'">
          PARAMETRES
        </h6>
      </li>

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/ligne"
          navText="Lignes">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">route</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/station" navText="Stations">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">home</i>
          </template>
        </sidenav-collapse>
      </li>
      
      <li class="nav-item"  >
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/vehicules"
          navText="Vehicules">
          <template v-slot:icon >
            <i class="material-icons-round opacity-10 fs-5" >directions_bus</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/deleg" navText="Delegues">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">location_on</i>
          </template>
        </sidenav-collapse>
      </li>

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/centre"
          navText="Centres">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">home</i>
          </template>
        </sidenav-collapse>
      </li>

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/type_ligne" navText="Types ligne">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">location_city</i>
          </template>
        </sidenav-collapse>
      </li>

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/param/agent" navText="Agents">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">person</i>
          </template>
        </sidenav-collapse>
      </li>


      <li class="mt-3 nav-item">
        <h6 class="text-xs ps-4 text-uppercase font-weight-bolder text-white"
          :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'">
          PLANNIFICATION
        </h6>
      </li>

      <li class="nav-item">
        <sidenav-collapse url="#" :aria-controls="''" v-bind:collapse="false" collapseRef="/trips" navText="Trips">
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">person</i>
          </template>
        </sidenav-collapse>
      </li>
      
    </ul>
    
  </div>
</template>
<script>
import SidenavCollapse from "./SidenavCollapse.vue";

export default {
  name: "SidenavList",
  props: {
    cardBg: String
  },
  data() {
    return {
      title: "Soft UI Dashboard PRO",
      controls: "dashboardsExamples",
      isActive: "active",
      isDown:true
    };
  },
  components: {
    SidenavCollapse
  },
  methods: {
    getRoute() {
      const routeArr = this.$route.path.split("/");
      return routeArr[1];
    },
    logout() {
      this.$store.dispatch("auth/logout");
    },
    toggleStyle(){
      this.isDown=!this.isDown;
    }
  }
};
</script>
<style scoped>

.iconDown{
  transition: .6s all cubic-bezier(.25,.56,.32,.96);
}
.iconUp{
  transform: rotateZ(180deg);
  transition: .6s all cubic-bezier(.25,.56,.32,.96);
}
</style>