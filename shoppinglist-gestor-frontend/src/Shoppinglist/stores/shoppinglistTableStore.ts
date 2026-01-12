import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'

export const useShoppinglistTableStore = defineStore('shoppinglistTableStore', () => {
  const shoppinglistTable = ref<ShoppinglistMetadata[]>([])
  function setShoppinglistTable(data: ShoppinglistMetadata[]) {
    shoppinglistTable.value = data
  }

  function addShoppinglist(data: ShoppinglistMetadata) {
    shoppinglistTable.value.push(data)
  }

  function getActiveShoppinglist() {
    return shoppinglistTable.value.filter((value) => value.isActive)
  }

  function getNoActiveShoppinglist() {
    return shoppinglistTable.value.filter((value) => !value.isActive)
  }

  function updateShoppinglistActive(id: number) {
    shoppinglistTable.value.forEach((element) => {
      if (element.idShoppinglist === id) {
        element.isActive = !element.isActive
      }
    })
  }

  function removeShoppinglist(id: number) {
    setShoppinglistTable(shoppinglistTable.value.filter((value) => value.idShoppinglist !== id))
  }

  return {
    shoppinglistTable,
    setShoppinglistTable,
    addShoppinglist,
    getActiveShoppinglist,
    getNoActiveShoppinglist,
    updateShoppinglistActive,
    removeShoppinglist,
  }
})
