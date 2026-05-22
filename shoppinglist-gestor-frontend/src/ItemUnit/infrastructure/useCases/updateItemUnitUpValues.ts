import { apiClient } from '@/Core/config/apiClient'
import type { RequestUpdateShoppinglistItemItemUnitsUp } from '../models/request/RequestUpdateShoppinglistItemItemUnitsUp'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'

async function updateItemUnitUpValues(
  idShoppinglistItem: number,
  data: RequestUpdateShoppinglistItemItemUnitsUp,
): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL'
    ? await InMemory()
    : await Api(idShoppinglistItem, data)
}

async function InMemory() {}

async function Api(idShoppinglistItem: number, data: RequestUpdateShoppinglistItemItemUnitsUp) {
  await apiClient.put(SHOPPINGLIST_ITEMS_ENDPOINTS.UPDATE_ITEM_UNIT_UP_V1(idShoppinglistItem), data)
}

export { updateItemUnitUpValues }
