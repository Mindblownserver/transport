import myApi from "@/services/myApi.service.js"

export const tripsModule={
    namespaced: true,
    state: {
      trips:[],
      loading:false,
      error:null
    },
    mutations: {
      setTrips(state, centres){
        state.centres = centres;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      }
    },
    actions: {
      getTrips({commit}, date){
        commit("setLoading", true);
        commit("setError", null);
        try{
            myApi.getTrips(date).then(res=>{
                commit("setTrips", res.data);
            })
        }catch(err){
            commit("setError", err);
        }finally{
            commit("setLoading", false);
        }
      }
    },
    getters: {},
  }