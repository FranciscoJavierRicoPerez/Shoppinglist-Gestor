import type { ResponseProductDetails } from '@/Product/infrastructure/models/ResponseProductDetails'
import responseGetProductDetails from '@/Product/infrastructure/mocks/responseGetProductDetails.json'
import { createProductDetails } from '@/Product/infrastructure/services/ProductService'
import type { ProductDetails } from '@/Product/domain/ProductDetails'
async function getProductDetails(id: number): Promise<ProductDetails> {
  return createProductDetails(await InMemory())
}

async function InMemory(): Promise<ResponseProductDetails> {
  return responseGetProductDetails as ResponseProductDetails
}

export { getProductDetails }
