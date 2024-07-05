import { createStore, createLogger } from "vuex";
import { auth } from "./auth.module";
import { profile } from "./profile.module";
import {sidebar} from "../features/sidenav/store/sidebar.module"

export default createStore({
  modules: {
    auth,
    profile,
    sidebar
  },
  state: {
    hideConfigButton: false,
    isPinned: true,
    showConfig: false,
    
    showSidenav: true,
    showNavbar: true,
    showFooter: true,
    showMain: true,
    isDarkMode: false,
    
  },
  mutations: {
    toggleConfigurator(state) {
      state.showConfig = !state.showConfig;
    },
    navbarMinimize(state) {
      const sidenav_show = document.querySelector(".g-sidenav-show");

      if (sidenav_show.classList.contains("g-sidenav-pinned")) {
        sidenav_show.classList.remove("g-sidenav-pinned");
        state.isPinned = true;
      } else {
        sidenav_show.classList.add("g-sidenav-pinned");
        state.isPinned = false;
      }
    },
    navbarFixed(state) {
      if (state.isNavFixed === false) {
        state.isNavFixed = true;
      } else {
        state.isNavFixed = false;
      }
    },
    toggleEveryDisplay(state) {
      state.showNavbar = !state.showNavbar;
      state.showSidenav = !state.showSidenav;
      state.showFooter = !state.showFooter;
    },
    toggleHideConfig(state) {
      state.hideConfigButton = !state.hideConfigButton;
    },
    color(state, payload) {
      state.color = payload;
    },
  },
  actions: {
    setColor({ commit }, payload) {
      commit("color", payload);
    },
  },
  getters: {},
  plugins:[createLogger]
});
