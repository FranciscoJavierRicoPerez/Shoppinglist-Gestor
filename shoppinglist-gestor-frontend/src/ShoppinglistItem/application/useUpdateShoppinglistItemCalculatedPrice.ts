import { updateShoppinglistItemTotalPrice } from '../infrastructure/useCases/updateShoppinglistItemTotalPrice'

export function useUpdateShoppinglistItemCalculatedPrice() {
  async function refetch(idShoppinglistItem: number): Promise<void> {
    await updateShoppinglistItemTotalPrice(idShoppinglistItem)
  }
  return { refetch }
}
