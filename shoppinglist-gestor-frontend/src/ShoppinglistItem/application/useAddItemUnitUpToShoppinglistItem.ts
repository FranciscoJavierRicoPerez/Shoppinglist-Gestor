import type { RequestCreateUnitData } from '@/ItemUnit/infrastructure/models/request/RequestCreateItemUnitData'
import { addItemUnitUpToShoppinglistItem } from '../infrastructure/useCases/addItemUnitUpToShoppinglistItem'

export function useAddItemUnitUpToShoppinglistItem() {
  async function refetch(idShoppinglistItem: number, data: RequestCreateUnitData): Promise<void> {
    await addItemUnitUpToShoppinglistItem(idShoppinglistItem, data)
  }
  return { refetch }
}
