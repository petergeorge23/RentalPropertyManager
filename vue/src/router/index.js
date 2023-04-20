import Vue from 'vue'
import Router from 'vue-router'

import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import About from '../views/AboutViews'
import Contact from '../views/ContactViews.vue';
import Property from '../views/PropertyViews.vue';
import LandlordPage from '../views/LandlordPageView.vue'
import EmployeePage from '../views/EmployeePageView.vue'
import RenterPage from '../views/RenterPageView.vue'
import PropertyDetails from '../views/PropertyDetails.vue'
import FeaturedProperties from '../views/FeaturedProperties.vue'
Vue.use(Router)


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: FeaturedProperties,
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    },
    {
      path: '/properties',
      name: 'properties',
      component: Property
    },
    {
      path: "/portal/landlord",
      name: "landlordPortal",
      component: LandlordPage,
    },
    {
      path: "/portal/employee",
      name: "employeePortal",
      component: EmployeePage,
    },
    {
      path: "/portal/renter",
      name: "renterPortal",
      component: RenterPage,
    },
    {
      path: '/property/:propertyId',
      name: 'property-details',
      component: PropertyDetails,
    },
    {
      path: '/:propertyId',
      name: 'property-details',
      component: PropertyDetails,
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});


export default router;
