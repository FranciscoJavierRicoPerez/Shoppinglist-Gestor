import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCreateShoppinglistItemFormStore = defineStore(
  'createShoppinglistItemFormStore',
  () => {
    const selectedProduct = ref<string>('') // Product name
    const productOptions = ref<string[]>([])

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
      verifyIsSelectedProductAlreadyExists,
    }
  },
)
