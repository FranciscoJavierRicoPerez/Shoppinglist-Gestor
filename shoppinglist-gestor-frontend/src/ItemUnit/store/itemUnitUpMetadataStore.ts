import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useItemUnitUpMetadataStore = defineStore('itemUnitUpMetadataStore', () => {
  const quantity = ref<number>(-1)
  const price = ref<number>(-1)
  const calculatedPrice = ref<number>(-1)

  function initValues(q: number, p: number, c: number) {
    quantity.value = q
    price.value = p
    calculatedPrice.value = c
  }

  function updateCalculatedPrice(quantity: number, price: number) {
    calculatedPrice.value = quantity * price
  }

  return {
    quantity,
    price,
    calculatedPrice,
    initValues,
    updateCalculatedPrice,
  }
})
