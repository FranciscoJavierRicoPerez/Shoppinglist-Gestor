import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useShoppinglistItemDetailsStore = defineStore('shoppinglistItemDetailsStore', () => {
  const sliName = ref<string>('')
  const sliCalculateSystem = ref<string>('')
  const sliAssignationToListDate = ref<string>('')
  const sliCalculatedPrice = ref<number>(-1)

  function initializateValues(
    name: string,
    calculateSystem: string,
    assignationDate: string,
    calculatedPrice: number,
  ) {
    ;((sliName.value = name),
      (sliCalculateSystem.value = calculateSystem),
      (sliAssignationToListDate.value = assignationDate),
      (sliCalculatedPrice.value = calculatedPrice))
  }

  return {
    sliName,
    sliCalculateSystem,
    sliAssignationToListDate,
    sliCalculatedPrice,
    initializateValues,
  }
})
