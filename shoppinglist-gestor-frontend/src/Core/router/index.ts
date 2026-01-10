import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'ShoppinglistView',
      component: () => import('@/v2/Shoppinglist/views/ShoppinglistView.vue'),
    },
    {
      path: '/information',
      name: 'InformationView',
      component: () => import('@/Information/views/InformationView.vue'),
    },
    {
      path: '/shoppinglist/:id',
      name: 'ShoppinglistDetailsView',
      component: () => import('@/Shoppinglist/views/ShoppinglistDetailView.vue'),
    },
    {
      path: '/product/:id',
      name: 'ProductDetailsView',
      component: () => import('@/Product/views/ProductDetails.vue'),
    },
  ],
})

export default router
