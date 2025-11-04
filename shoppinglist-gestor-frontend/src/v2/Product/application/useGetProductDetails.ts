import type { ProductDetails } from '@/Product/domain/ProductDetails'
import { getProductDetails } from '@/Product/infrastructure/useCases/getProductDetails'

export function useGetProductDetails() {
  async function refetch(): Promise<ProductDetails> {
    return await getProductDetails(1)
  }
  return { refetch }
}
