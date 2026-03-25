import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'

async function updateShoppinglistTotalPrice(idShoppinglist: number): Promise<number> {
  const response =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglist)
  return response
}
async function InMemory(): Promise<number> {
  return 0
}
async function Api(idShoppinglist: number): Promise<number> {
  const response = await apiClient.put(SHOPPINGLIST_ENDPOINTS.UPDATE_TOTAL_PRICE_V1(idShoppinglist))
  return response.data
}

export { updateShoppinglistTotalPrice }
