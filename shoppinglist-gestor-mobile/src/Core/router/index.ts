import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '@/Core/views/HomePage.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/shoppinglist'
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/message/:id',
    component: () => import('@/Core/views/ViewMessagePage.vue')
  },
  {
    path: '/shoppinglist',
    name: 'Shoppinglist',
    component:()=> import('@/Shoppinglist/views/ShoppinglistView.vue')
  },
  {
    path: '/shoppinglist/:id',
    name: 'ShoppinglistDetailsView',
    component: () => import('@/Shoppinglist/views/ShoppinglistDetailViewV2.vue')
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
