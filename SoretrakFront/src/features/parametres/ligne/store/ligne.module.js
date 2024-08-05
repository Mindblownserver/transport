import myApi from "@/services/myApi.service.js"

export const ligneModule={
    namespaced: true,
    state: {
      lignes:[],
      loading:true,
      error:null
    },
    mutations: {
      setLignes(state, lignes){
        state.lignes = lignes;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      }
    },
    actions: {
       async getLignes({commit}){
        commit("setLoading", true);
        commit("setError", null);
        try{
            const res = await myApi.getLignes();
            commit("setLignes", res.data);
        }catch(err){
            commit("setError", err);
        }finally{
            commit("setLoading", false);
        }
      }
    },
    getters: {},
  }