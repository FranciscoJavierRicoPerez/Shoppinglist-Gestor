import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { RequestCreateShoppinglistItemForm } from '@/ShoppinglistItem/infrastructure/models/request/RequestCreateShoppinglistItemForm'

export const useShoppinglistItemStore = defineStore('shoppinglistItemStore', () => {
  const createShoppinglistItemForm = ref<RequestCreateShoppinglistItemForm>()

  return {
    createShoppinglistItemForm,
  }
})
