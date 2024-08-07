import { createStore, createLogger } from "vuex";
import { auth } from "./auth.module";
import { profile } from "./profile.module";
import {sidebar} from "../features/sidenav/store/sidebar.module"
import {centreModule} from "../features/parametres/centre/store/centre.module";
import { delegModule } from "../features/parametres/drDeleg/service/deleg.module";
import { typeLigneModule } from "../features/parametres/typeLigne/service/typeligne.module";
import { stationModule } from "../features/parametres/station/store/station.module";
import { tripsModule } from "../features/plannification/store/trips.module";
import { dashboardModule } from "../features/dashboard/store/dashboard.module";
import { vehiculeModule } from "../features/parametres/vehicule/store/vehicule.module";
import { agentModule } from "../features/parametres/agent/store/agent.module";
import { ligneModule } from "../features/parametres/ligne/store/ligne.module";

export default createStore({
  modules: {
    auth,
    profile,
    sidebar,
    centreModule,
    delegModule,
    typeLigneModule,
    stationModule,
    tripsModule,
    dashboardModule,
    vehiculeModule,
    agentModule,
    ligneModule
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
    toggleDarkMode(state){
      state.isDarkMode = !state.isDarkMode;
    }
  },
  actions: {
    setColor({ commit }, payload) {
      commit("color", payload);
    },
  },
  getters: {},
  plugins:[createLogger]
});
