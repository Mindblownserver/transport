import myApi from "@/services/myApi.service.js"
export const agentModule={
    namespaced: true,
    state: {
      agents:[],
      loading:false,
      error:null,
    },
    mutations: {
      setAgents(state, agents){
        state.agents = agents;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      },
    },
    actions: {
      async getAgents({commit}){
        commit("setLoading", true);
        commit("setError", null);
        try{
            const res = await myApi.getAgents();
            commit("setAgents", res.data);
            
        }catch(err){
            commit("setError", err);
        }finally{

            commit("setLoading", false);
        }
      },
    },
    getters: {},
  }