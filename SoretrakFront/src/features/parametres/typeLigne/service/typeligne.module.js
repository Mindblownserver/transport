import myApi from "@/services/myApi.service.js"

export const typeLigneModule={
  namespaced: true,
  state: {
    typeLignes:[],
    loading:false,
    error:null
  },
  mutations: {
    setTypeLigne(state, typeLi){
      state.typeLignes = typeLi;
    },
    setLoading(state,loading){
      state.loading = loading
    },
    setError(state,err){
      state.error =err;
    }
  },
  actions: {
    getTypeLigne({commit}){
      commit("setLoading", true);
      commit("setError", null);
      try{
          myApi.getTypeLignes().then(res=>{
              commit("setTypeLigne", res.data);
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