import { updateShoppinglistTotalPrice } from '../infrastructure/useCases/updateShoppinglistTotalPrice'

export function useUpdateShoppinglistTotalPrice() {
  async function refetch(idShoppinglist: number): Promise<void> {
    await updateShoppinglistTotalPrice(idShoppinglist)
  }
  return { refetch }
}
