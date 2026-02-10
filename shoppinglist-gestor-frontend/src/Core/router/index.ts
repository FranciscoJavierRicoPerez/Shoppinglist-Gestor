import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'ShoppinglistView',
      component: () => import('@/Shoppinglist/views/ShoppinglistView.vue'),
    },
    {
      path: '/information',
      name: 'InformationView',
      component: () => import('@/Information/views/InformationView.vue'),
    },
    {
      path: '/shoppinglist/:id',
      name: 'ShoppinglistDetailsView',
      component: () => import('@/Shoppinglist/views/ShoppinglistDetailsV2View.vue'),
    },
  ],
})

export default router
