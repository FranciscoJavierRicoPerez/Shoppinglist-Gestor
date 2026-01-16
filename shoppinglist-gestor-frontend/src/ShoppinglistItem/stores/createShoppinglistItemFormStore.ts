import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCreateShoppinglistItemFormStore = defineStore(
  'createShoppinglistItemFormStore',
  () => {
    const selectedProduct = ref<string>('') // Product name
    const productOptions = ref<string[]>([])

    const selectedCalculateSystem = ref<number>(-1)

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

    return {
      selectedProduct,
      productOptions,
      selectedCalculateSystem,
      verifyIsSelectedProductAlreadyExists,
    }
  },
)
