import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCreateShoppinglistItemFormStore = defineStore(
  'createShoppinglistItemFormStore',
  () => {
    const selectedProduct = ref<string>('') // Product name
    const productOptions = ref<string[]>([])

    const selectedCalculateSystem = ref<number>(-1)

    const quantity = ref<number | null>(null)
    const unitaryPrice = ref<number | null>(null)

    const priceKg = ref<number | null>(null)
    const weight = ref<number | null>(null)

    const shoppinglistItemPrice = ref<number | null>(null)

    // TODO: VALIDAR FUNCIONAMIENTO DE ESTO
    function verifyIsSelectedProductAlreadyExists(
      selectedProduct: string,
      productOptions: string[],
    ): boolean {
      let list: string[] = productOptions.filter((element) => {
        element === selectedProduct
      })
      return list.length > 0
    }

    function setValuesDefault(): void {
      selectedProduct.value = ''
      ;((selectedCalculateSystem.value = -1), (quantity.value = null))
      unitaryPrice.value = null
      priceKg.value = null
      weight.value = null
      shoppinglistItemPrice.value = null
    }

    return {
      selectedProduct,
      productOptions,
      selectedCalculateSystem,
      verifyIsSelectedProductAlreadyExists,
      quantity,
      unitaryPrice,
      priceKg,
      weight,
      shoppinglistItemPrice,
      setValuesDefault,
    }
  },
)
