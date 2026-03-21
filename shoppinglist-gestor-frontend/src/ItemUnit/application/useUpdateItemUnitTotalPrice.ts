import { updateItemUnitTotalPrice } from '../infrastructure/useCases/updateItemUnitTotalPrice'

export function useUpdateItemUnitTotalPrice() {
  async function refetch(idItemUnit: number, newTotalPrice: number): Promise<void> {
    await updateItemUnitTotalPrice(idItemUnit, newTotalPrice)
  }
  return { refetch }
}
