import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'

async function updateShoppinglistItemTotalPrice(idShoppinglistItem: number): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglistItem)
}
async function Api(idShoppinglistItem: number): Promise<void> {
  await apiClient.put(SHOPPINGLIST_ITEMS_ENDPOINTS.UPDATE_CALCULATED_PRICE_V1(idShoppinglistItem))
}
async function InMemory(): Promise<void> {}

export { updateShoppinglistItemTotalPrice }
