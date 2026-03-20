import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'

async function updateShoppinglistTotalPrice(idShoppinglist: number): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglist)
}
async function InMemory(): Promise<void> {}
async function Api(idShoppinglist: number): Promise<void> {
  await apiClient.put(SHOPPINGLIST_ENDPOINTS.UPDATE_TOTAL_PRICE_V1(idShoppinglist))
}

export { updateShoppinglistTotalPrice }
