import { defineStore } from 'pinia'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { ref } from 'vue'

export const useShoppinglistStore = defineStore('shoppinglistStore', () => {
  const shoppinglistArray = ref<Shoppinglist[]>([])

  function setShoppinglistArray(data: Shoppinglist[]) {
    shoppinglistArray.value = data
  }

  function addShoppinglist(data: Shoppinglist) {
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
      if (element.id === id) {
        element.isActive = !element.isActive
      }
    })
  }

  function removeShoppinglist(id: number) {
    setShoppinglistArray(shoppinglistArray.value.filter((value) => value.id !== id))
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
