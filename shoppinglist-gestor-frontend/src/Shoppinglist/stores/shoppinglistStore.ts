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

  return { shoppinglistArray, setShoppinglistArray, addShoppinglist }
})
