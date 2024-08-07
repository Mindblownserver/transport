import myApi from "@/services/myApi.service.js"
export const stationModule={
    namespaced: true,
    state: {
      stations:[],
      loading:true,
      error:null,
    },
    mutations: {
      setStations(state, stations){
        state.stations = stations;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      },
    },
    actions: {
      async getStation({commit}){
        commit("setError", null);
        try{
            const res = await myApi.getStations()
            commit("setStations", res.data);
            
        }catch(err){
            commit("setError", err);
        }finally{

            commit("setLoading", false);
        }
      },
    },
    getters: {},
  }