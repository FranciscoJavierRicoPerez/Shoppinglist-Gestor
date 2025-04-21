import type { ProductDetails } from '../domain/ProductDetails'
import { getProductDetails } from '../infrastructure/useCases/getProductDetails'

export function useGetProductDetails() {
  async function refetch(): Promise<ProductDetails> {
    return await getProductDetails(1)
  }
  return { refetch }
}
