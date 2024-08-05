import myApi from "@/services/myApi.service.js"

export const delegModule={
  namespaced: true,
  state: {
    deleg:[],
    loading:false,
    error:null
  },
  mutations: {
    setDeleg(state, deleg){
      state.deleg = deleg;
    },
    setLoading(state,loading){
      state.loading = loading
    },
    setError(state,err){
      state.error =err;
    }
  },
  actions: {
    async getDeleg({commit}){
      commit("setLoading", true);
      commit("setError", null);
      try{
          const res = await myApi.getDeleg()
          commit("setDeleg", res.data);
      }catch(err){
          commit("setError", err);
      }finally{
          commit("setLoading", false);
      }
    }
  },
  getters: {},
}