import myApi from "@/services/myApi.service.js"
export const agentModule={
    namespaced: true,
    state: {
      agents:[],
      loading:true,
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
      getAgents({commit}){
        commit("setError", null);
        try{
            myApi.getAgents().then(res=>{
              commit("setAgents", res.data);
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