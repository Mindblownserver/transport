import myApi from "@/services/myApi.service.js"

export const centreModule={
    namespaced: true,
    state: {
      centres:[],
      loading:false,
      error:null
    },
    mutations: {
      setCentres(state, centres){
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
      getCentres({commit}){
        commit("setLoading", true);
        commit("setError", null);
        try{
            myApi.getCentres().then(res=>{
                commit("setCentres", res.data);
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