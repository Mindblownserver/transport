<template>
  <nav class="shadow-none navbar navbar-main navbar-expand-lg border-radius-xl" v-bind="$attrs" id="navbarBlur"
    data-scroll="true" :class="isAbsolute ? 'mt-4' : 'mt-0'">
    <div class="px-3 py-1 container-fluid">
      <breadcrumbs :currentPage="currentRouteName" :color="color" />
      <div class="mt-2 collapse navbar-collapse mt-sm-0 me-md-0 me-sm-4" :class="isRTL ? 'px-0' : 'me-sm-4'" id="navbar">
        <div class="pe-md-3 d-flex align-items-center" :class="isRTL ? 'me-md-auto' : 'ms-md-auto'">
          
        </div>
        <ul class="navbar-nav justify-content-end">
          <li class="nav-item d-xl-none ps-3 d-flex align-items-center">
            <a href="#" @click="toggleSidebar" class="p-0 nav-link text-body lh-1" id="iconNavbarSidenav">
              <div class="sidenav-toggler-inner">
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
              </div>
            </a>
          </li>

          <li class="px-3 nav-item d-flex align-items-center">
            <a class="p-0 nav-link lh-1" @click="toggleTheme" :class="color ? color : 'text-body'">
              <i class="material-icons fixed-plugin-button-nav cursor-pointer">
                contrast
              </i>
            </a>
          </li>
          
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import Breadcrumbs from "../Breadcrumbs.vue";
import { mapMutations, mapState } from "vuex";

export default {
  name: "navbar",
  data() {
    return {
      showMenu: false,
    };
  },
  props: ["minNav", "color"],
  created() {
    this.minNav;
  },
  methods: {
    ...mapMutations(["navbarMinimize","toggleDarkMode"]),

    toggleSidebar() {
      this.navbarMinimize();
    },

    logout(){
      this.$store.dispatch("auth/logout");
    },
    toggleTheme(){
      const element = document.querySelector('html');
      element.classList.toggle('dark-version');
      this.$store.commit("toggleDarkMode")
    }
  },
  components: {
    Breadcrumbs,
  },
  computed: {
    ...mapState(["isRTL", "isAbsolute"]),

    currentRouteName() {
      //console.log(this.$route.name||this.$route.meta.name)
      return this.$route.name;//||this.$route.meta.name;
    },
  },
};
</script>
