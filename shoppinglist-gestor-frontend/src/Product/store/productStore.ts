import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useProductStore = defineStore('productStore', () => {
  const productSelector = ref()
  return {
    productSelector,
  }
})
