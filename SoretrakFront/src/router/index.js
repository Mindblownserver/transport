import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "../features/dashboard/views/Dashboard.vue";
import Tables from "../views/Tables.vue";
import Billing from "../views/Billing.vue";
import RTL from "../views/Rtl.vue";
import Notifications from "../views/Notifications.vue";
import Profile from "../views/Profile.vue";
import SignIn from "../views/SignIn.vue";
import SignUp from "../views/SignUp.vue";

import UserProfile from "../views/examples-api/profile/UserProfile.vue";
import Users from "../views/examples-api/users/UsersList.vue";
import delegRoute from "../features/parametres/drDeleg/router/delegRoute";
import stationRoute from "../features/parametres/station/router/stationRoute";
import ligneRoute from "../features/parametres/ligne/router/ligneRoute";
import vehiculeRoute from "../features/parametres/vehicule/router/vehiculeRoute";
import centreRoute from "../features/parametres/centre/router/centreRoute";
import typeLignesRoute from "../features/parametres/typeLigne/router/typeLignesRoute";
import tripsRoute from "../features/plannification/router/tripsRoute";
import agentRoute from "../features/parametres/agent/router/agentRoute";

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/sign-in",
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
  },
  ...delegRoute, 
  ...stationRoute, 
  ...ligneRoute, 
  ...vehiculeRoute, 
  ...centreRoute, 
  ...typeLignesRoute, 
  ...tripsRoute,
  ...agentRoute,
  {
    path: "/tables",
    name: "Tables",
    component: Tables,
  },
  {
    path: "/billing",
    name: "Billing",
    component: Billing,
  },
  {
    path: "/rtl-page",
    name: "RTL",
    component: RTL,
    meta: { requiresAuth: true, roles: ['USER'] },

  },
  {
    path: "/notifications",
    name: "Notifications",
    component: Notifications,
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/sign-in",
    name: "SignIn",
    component: SignIn,
  },
  {
    path: "/sign-up",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/login",
    name: "Login",
    redirect:"/sign-in"
  },
  {
    path: "/signup",
    name: "Signup",
    redirect:"sign-up"
  },
  
  {
    path: "/user-profile",
    name: "User Profile",
    component: UserProfile
  },
  {
    path: '/users',
    name: "Users",
    component: Users
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});


router.beforeEach((to, from, next) => {
  const loggedIn = JSON.parse(localStorage.getItem('user_free'));
  const userRole = loggedIn ? loggedIn.role : null;
  console.log("ahayya: "+userRole)

  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!loggedIn) {
      return next({ name: 'SignIn' });
    }

    if (to.meta.roles && !to.meta.roles.includes(userRole)) {
      return next({ name: 'Dashboard' }); 
      
    }
  }

  next();
});

export default router;
