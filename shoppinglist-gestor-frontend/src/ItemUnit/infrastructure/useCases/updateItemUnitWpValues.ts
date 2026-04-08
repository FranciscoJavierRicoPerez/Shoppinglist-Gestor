import { apiClient } from '@/Core/config/apiClient'
import type { RequestUpdateItemUnitWpData } from '../models/request/RequestUpdateItemUnitWpData'
import { WP_ITEM_UNIT_ENDPOINTS } from '@/Core/config/config'

async function updateItemUnitWpValues(
  idItemUnitWp: number,
  data: RequestUpdateItemUnitWpData,
): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idItemUnitWp, data)
}

async function InMemory() {}
async function Api(idItemUnitWp: number, data: RequestUpdateItemUnitWpData): Promise<void> {
  await apiClient.put(WP_ITEM_UNIT_ENDPOINTS.UPDATE_ITEM_UNIT_WP_V1(idItemUnitWp), data)
}

export { updateItemUnitWpValues }
