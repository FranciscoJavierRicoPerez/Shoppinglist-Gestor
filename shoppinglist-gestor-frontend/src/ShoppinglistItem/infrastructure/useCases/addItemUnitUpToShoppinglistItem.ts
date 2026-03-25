import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'
import type { RequestCreateUnitData } from '@/ItemUnit/infrastructure/models/request/RequestCreateItemUnitData'

async function addItemUnitUpToShoppinglistItem(
  idShoppinglistItem: number,
  data: RequestCreateUnitData,
): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL'
    ? await InMemory()
    : await Api(idShoppinglistItem, data)
}

async function Api(idShoppinglistItem: number, data: RequestCreateUnitData): Promise<void> {
  await apiClient.post(SHOPPINGLIST_ITEMS_ENDPOINTS.ADD_ITEM_UNIT_UP_V1(idShoppinglistItem), data)
}
async function InMemory(): Promise<void> {}

export { addItemUnitUpToShoppinglistItem }
