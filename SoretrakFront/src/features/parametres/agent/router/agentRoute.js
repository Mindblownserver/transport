import Agent from "../views/Agent.vue"
export default [
    {
        path:"/param/agent/chauffeur",
        component: Agent,
        name:"Chauffeur"
    },
    {
        path:"/param/agent/receveur",
        component: Agent,
        name:"Receveur"
    },
    {
        path:"/param/agent/chauff-recev",
        component: Agent,
        name:"Chauffeur-Receveur"
    }
]