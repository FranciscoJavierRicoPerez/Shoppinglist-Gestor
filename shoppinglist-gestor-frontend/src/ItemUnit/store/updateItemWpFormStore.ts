import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUpdateItemWpFormStore = defineStore('updateItemWpFormStore', () => {
  const newWeight = ref<number | null>(null)
  const newPriceKg = ref<number | null>(null)
  const newProductPrice = ref<number | null>(null)
  const newShoppinglistPrice = ref<number | null>(null)

  function calculateNewShoppinglistPrice(
    oldSlPrice: number,
    oldProductPrice: number,
    newProductPrice: number,
  ) {
    newShoppinglistPrice.value = oldSlPrice - oldProductPrice + newProductPrice
  }

  return {
    newPriceKg,
    newWeight,
    newProductPrice,
    newShoppinglistPrice,
    calculateNewShoppinglistPrice,
  }
})
