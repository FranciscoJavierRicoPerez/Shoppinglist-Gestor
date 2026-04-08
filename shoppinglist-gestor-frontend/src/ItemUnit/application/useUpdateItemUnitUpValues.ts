import type { RequestUpdateShoppinglistItemItemUnitsUp } from '../infrastructure/models/request/RequestUpdateShoppinglistItemItemUnitsUp'
import { updateItemUnitUpValues } from '../infrastructure/useCases/updateItemUnitUpValues'

export function useUpdateItemUnitUpValues() {
  async function refetch(
    idShoppinglistItem: number,
    data: RequestUpdateShoppinglistItemItemUnitsUp,
  ): Promise<void> {
    await updateItemUnitUpValues(idShoppinglistItem, data)
  }
  return { refetch }
}
