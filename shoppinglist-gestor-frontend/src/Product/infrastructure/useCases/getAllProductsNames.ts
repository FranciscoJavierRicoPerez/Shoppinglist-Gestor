import type { ProductsNames } from '@/Product/domain/ProductsNames'
import type { ResponseProductsNames } from '@/Product/infrastructure/models/response/ResponseProductsNames'
import responseGetAllProductsNames from '@/Product/infrastructure/mocks/responseGetAllProductsNames.json'
import axios from 'axios'
import { createProductsNames } from '../services/ProductService'
async function getAllProductsNames(): Promise<ProductsNames> {
  const response: ResponseProductsNames =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createProductsNames(response)
}

async function InMemory(): Promise<ResponseProductsNames> {
  return responseGetAllProductsNames as ResponseProductsNames
}

async function Api(): Promise<ResponseProductsNames> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/product/v1/names'
  const response = await axios.get(url)
  return response.data
}

export { getAllProductsNames }
