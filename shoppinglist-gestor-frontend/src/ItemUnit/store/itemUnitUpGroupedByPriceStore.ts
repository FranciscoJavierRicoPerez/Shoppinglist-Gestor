import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { ItemUnitUpMetadata } from '../domain/ItemUnitUpMetadata'

export const useItemUnitUpGroupedByPriceStore = defineStore('itemUnitUpGroupedByPriceStore', () => {
  const totalPrice = ref<number>(-1)
  const itemsGrouped = ref<ItemUnitUpMetadata[]>([])

  // Se puede utilizar el precio como identificador por que solo va a existir un precio, pero lo recomendable
  // seria que al refactorizar el BE, tambien lo cambie aqui para que sea por identificador
  function removeItem(price: number): ItemUnitUpMetadata[] {
    return itemsGrouped.value.filter((element) => element.price !== price)
  }

  function updateItemsGroupedList(newList: ItemUnitUpMetadata[]): void {
    itemsGrouped.value = newList
  }

  function updateTotalPrice(): void {
    let result: number = 0
    itemsGrouped.value.forEach((element) => {
      result += element.calculatedPrice
    })
    totalPrice.value = result
  }

  function add(data: ItemUnitUpMetadata): void {
    itemsGrouped.value.push(data)
  }

  return {
    totalPrice,
    itemsGrouped,
    removeItem,
    updateItemsGroupedList,
    updateTotalPrice,
    add,
  }
})
