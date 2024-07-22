import myApi from "@/services/myApi.service.js"
export const stationModule={
    namespaced: true,
    state: {
      stations:[],
      loading:false,
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
      getStation({commit}){
        commit("setLoading", true);
        commit("setError", null);
        try{
            myApi.getStations().then(res=>{
              commit("setStations", res.data);
            })
            
        }catch(err){
            commit("setError", err);
        }finally{

            commit("setLoading", false);
        }
      },
    },
    getters: {},
  }