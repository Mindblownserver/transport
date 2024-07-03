export const sidebar = {
    namespaced:true,
    state: {
        sidebarType: "bg-white",
        isRTL: false,
        color: "warning",
        isNavFixed: false,
        isAbsolute: false,
        showNavs: true,
        
        navbarFixed:
          "position-sticky blur shadow-blur left-auto top-1 z-index-sticky px-0 mx-4",
        absolute: "position-absolute px-4 mx-0 w-100 z-index-2",
    },
    mutations:{
        color(state, payload) {
            state.color = payload;
          },
    },
    actions: {
        setColor({ commit }, payload) {
            commit("color", payload);
        },
    },
}