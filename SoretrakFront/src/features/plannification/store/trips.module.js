import myApi from "@/services/myApi.service.js"
import { ResourceArray } from "../utils/Resource";

export const tripsModule={
    namespaced: true,
    state: {
      trips:[],
      ligneFromTrips: new ResourceArray(),
      agentFromTrips: new ResourceArray(),
      vehiculeFromTrips: new ResourceArray(),
      loading:true,
      error:null
    },
    mutations: {
      setTrips(state, trips){
        state.trips = trips;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      },
      setLignes(state,lignes){
        state.ligneFromTrips=lignes;
      },
      setAgents(state,agents){
        state.agentFromTrips=agents;
      },
      setVehicules(state,vehicules){
        state.vehiculeFromTrips=vehicules;
      },
    },
    actions: {
      async getTrips({ commit }, date) {

        try {
          const res = await myApi.getTrips(date);
          commit("setTrips", res.data);
        } catch (error) {
          commit("setError", error);
        } finally {
          commit("setLoading", false);
        }
      }
    },
    getters: {
      getLigneById:(state)=>(id)=>{
        return state.ligneFromTrips.getResource(id);
      },

      getAgentById:(state)=>(id)=>{
        return state.agentFromTrips.getResource(id);
      },

      getVehiculeById:(state)=>(id)=>{
        return state.vehiculeFromTrips.getResource(id);
      },

    },
  }