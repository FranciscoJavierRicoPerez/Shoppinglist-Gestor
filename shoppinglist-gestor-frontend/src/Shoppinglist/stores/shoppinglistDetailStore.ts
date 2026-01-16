import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { ShoppinglistDetailsMetadata } from '../domain/ShoppinglistDetailsMetadata'

// Store para manejar y mostrar la información del detalle de la lista de la compra
export const useShoppinglistDetailStore = defineStore('shoppinglistDetailStore', () => {
  const shoppinglistDetails = ref<ShoppinglistDetailsMetadata>()
  return {
    shoppinglistDetails,
  }
})
