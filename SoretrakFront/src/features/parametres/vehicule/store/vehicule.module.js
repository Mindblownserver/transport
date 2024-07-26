import myApi from "@/services/myApi.service.js"
export const vehiculeModule={
    namespaced: true,
    state: {
      vehicules:[],
      loading:true,
      error:null,
    },
    mutations: {
      setVehicules(state, vehic){
        state.vehicules = vehic;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      },
    },
    actions: {
      getVehicule({commit}){
        commit("setError", null);
        try{
            myApi.getVehicules().then(res=>{
              commit("setVehicules", res.data);
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