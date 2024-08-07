import TypeLignes from "../views/TypeLignes.vue"
export default [
    {
      path: "/param/type_ligne",
      name: "TypeLignes",
      component:TypeLignes,
      meta: { requiresAuth: true, roles: ['admin'] } // Example meta field

    }
];  