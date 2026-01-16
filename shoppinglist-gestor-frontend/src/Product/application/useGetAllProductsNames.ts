import type { ProductsNames } from '@/Product/domain/ProductsNames'
import { getAllProductsNames } from '@/Product/infrastructure/useCases/getAllProductsNames'

export function useGetAllProductsNames() {
  async function refetch(): Promise<ProductsNames> {
    return await getAllProductsNames()
  }

  return {
    refetch,
  }
}
