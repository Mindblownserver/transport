<template>
  <!-- bg-gray-50 #F0F2F5  #333537-->
  <div :style="`background-color: ${isDarkMode?'#1A2035': '#F0F2F5'}`">
    <sidenav
    :custom_class="getColor"
    :class="[isRTL ? 'fixed-end' : 'fixed-start']"
    v-if="showSidenav"
    />
    <main class="main-content position-relative max-height-vh-100 h-100 overflow-x-hidden">
      <navbar
        :class="[isNavFixed ? navbarFixed : '', isAbsolute ? absolute : '']"
        :color="isAbsolute ? 'text-white opacity-8' : ''"
        :minNav="navbarMinimize"
        v-if="showNavbar"/>
      <router-view />
      <app-footer v-show="showFooter" />
    </main>
  </div>
  
</template>
<script>
import Sidenav from "./features/sidenav/components/";
import Navbar from "@/components/Navbars/Navbar.vue";
import { mapMutations, mapState } from "vuex";

export default {
  name: "App",
  components: {
    Sidenav,
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
      "isDarkMode"
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
  --bg-gradient-success: linear-gradient(195deg, #66bb6a 0%, #43a047 100%);
  --simple-soretrak-color: #fb8c00;
  --soretrak-color: linear-gradient(195deg, #ffa726 0%, #fb8c00 100%); 
}
</style>
