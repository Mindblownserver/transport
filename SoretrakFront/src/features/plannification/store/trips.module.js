import myApi from "@/services/myApi.service.js"

export const tripsModule={
    namespaced: true,
    state: {
      trips:[],
      loading:false,
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
      }
    },
    actions: {
      async getTrips({ commit }, date) {
        commit("setLoading", true);
        commit("setError", null);
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
    getters: {},
  }