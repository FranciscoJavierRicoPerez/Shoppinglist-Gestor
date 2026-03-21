import { apiClient } from '@/Core/config/apiClient'
import { ITEM_UNIT_ENDPOINTS } from '@/Core/config/config'

async function updateItemUnitTotalPrice(idItemUnit: number, newTotalPrice: number): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL'
    ? await InMemory()
    : await Api(idItemUnit, newTotalPrice)
}
async function Api(idItemUnit: number, newTotalPrice: number): Promise<void> {
  await apiClient.put(ITEM_UNIT_ENDPOINTS.UPDATE_ITEM_UNIT_TOTAL_PRICE_V1(idItemUnit), {
    newTotalPrice: newTotalPrice,
  })
}
async function InMemory(): Promise<void> {}

export { updateItemUnitTotalPrice }
