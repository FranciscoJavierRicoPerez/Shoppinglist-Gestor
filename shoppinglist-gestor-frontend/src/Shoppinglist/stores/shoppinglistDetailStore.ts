import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { ShoppinglistDetailsMetadata } from '../domain/ShoppinglistDetailsMetadata'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'

// Store para manejar y mostrar la información del detalle de la lista de la compra
export const useShoppinglistDetailStore = defineStore('shoppinglistDetailStore', () => {
  const shoppinglistDetails = ref<ShoppinglistDetailsMetadata>()
  const items = ref<ShoppinglistItemMetadata[]>([])

  const totalPrice = ref<number | null>(null)

  function addItem(data: ShoppinglistItemMetadata): void {
    items.value.push(data)
  }

  function removeItem(shoppinglistItemId: number): ShoppinglistItemMetadata[] {
    return items.value.filter((element) => element.idShoppinglistItem !== shoppinglistItemId)
  }

  function updateItemsList(newItemList: ShoppinglistItemMetadata[]): void {
    items.value = newItemList
  }

  function updateTotalPrice(add: boolean, value: number) {
    if (totalPrice.value) {
      add ? (totalPrice.value += value) : (totalPrice.value -= value)
    }
  }

  return {
    shoppinglistDetails,
    items,
    addItem,
    removeItem,
    updateItemsList,
    totalPrice,
    updateTotalPrice,
  }
})
