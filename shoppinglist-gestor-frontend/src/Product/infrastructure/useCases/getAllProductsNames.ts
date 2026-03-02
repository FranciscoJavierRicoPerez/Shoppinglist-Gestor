import type { ProductsNames } from '@/Product/domain/ProductsNames'
import type { ResponseProductsNames } from '@/Product/infrastructure/models/response/ResponseProductsNames'
import responseGetAllProductsNames from '@/Product/infrastructure/mocks/responseGetAllProductsNames.json'
import { createProductsNames } from '../services/ProductService'
import { apiClient } from '@/Core/config/apiClient'
import { PRODUCTS_ENDPOINTS } from '@/Core/config/config'
async function getAllProductsNames(): Promise<ProductsNames> {
  const response: ResponseProductsNames =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createProductsNames(response)
}

async function InMemory(): Promise<ResponseProductsNames> {
  return responseGetAllProductsNames as ResponseProductsNames
}

async function Api(): Promise<ResponseProductsNames> {
  const response = await apiClient.get(PRODUCTS_ENDPOINTS.GET_NAMES_V1())
  return response.data
}

export { getAllProductsNames }
