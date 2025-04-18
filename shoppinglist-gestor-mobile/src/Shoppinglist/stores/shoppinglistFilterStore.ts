import { defineStore } from 'pinia'
import {
  defaultShoppinglistFilter,
  type ShoppinglistFilter,
} from '@/Shoppinglist/domain/ShoppinglistFilter'
import { ref } from 'vue'

export const useShoppinglistFilterStore = defineStore('shoppinglistFilterStore', () => {
  const shoppinglistFilterForm = ref<ShoppinglistFilter>({ ...defaultShoppinglistFilter })

  const actualPanelId = ref<Number>(-1)

  function setShoppinglistFilterForm(data: ShoppinglistFilter) {
    shoppinglistFilterForm.value = data
  }

  function setActualPanelId(actualPanel: number) {
    actualPanelId.value = actualPanel
  }

  return {
    shoppinglistFilterForm,
    actualPanelId,
    setShoppinglistFilterForm,
    setActualPanelId,
  }
})
