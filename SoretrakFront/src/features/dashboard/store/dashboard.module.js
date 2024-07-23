import myApi from "@/services/myApi.service.js"

export const dashboardModule={
    namespaced: true,
    state: {
      statistics:Object,
      loading:true,
      error:null
    },
    mutations: {
      setStatistics(state, statistics){
        state.statistics = statistics;
      },
      setLoading(state,loading){
        state.loading = loading
      },
      setError(state,err){
        state.error =err;
      },
    },
    actions: {
      async getStatisticsByDate({ commit }, date) {

        try {
          const res = await myApi.getStatisticsByDate(date);
          commit("setStatistics", res.data);
        } catch (error) {
          commit("setError", error);
        } finally {
          commit("setLoading", false);
        }
      }
    },
    
  }