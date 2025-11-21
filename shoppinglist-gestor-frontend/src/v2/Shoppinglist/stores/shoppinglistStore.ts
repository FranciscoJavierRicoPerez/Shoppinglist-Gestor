import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'

export const useShoppinglistStore = defineStore('shoppinglistStore', () => {
  const shoppinglistArray = ref<ShoppinglistMetadata[]>([])

  function setShoppinglistArray(data: ShoppinglistMetadata[]) {
    shoppinglistArray.value = data
  }

  function addShoppinglist(data: ShoppinglistMetadata) {
    shoppinglistArray.value.push(data)
  }

  function getActiveShoppinglist() {
    return shoppinglistArray.value.filter((value) => value.isActive)
  }

  function getNoActiveShoppinglist() {
    return shoppinglistArray.value.filter((value) => !value.isActive)
  }

  function updateShoppinglistActive(id: number) {
    shoppinglistArray.value.forEach((element) => {
      if (element.idShoppinglist === id) {
        element.isActive = !element.isActive
      }
    })
  }

  function removeShoppinglist(id: number) {
    setShoppinglistArray(shoppinglistArray.value.filter((value) => value.idShoppinglist !== id))
  }

  return {
    shoppinglistArray,
    setShoppinglistArray,
    addShoppinglist,
    getActiveShoppinglist,
    getNoActiveShoppinglist,
    updateShoppinglistActive,
    removeShoppinglist,
  }
})
