import { updateShoppinglistTotalPrice } from '../infrastructure/useCases/updateShoppinglistTotalPrice'

export function useUpdateShoppinglistTotalPrice() {
  async function refetch(idShoppinglist: number): Promise<number> {
    return await updateShoppinglistTotalPrice(idShoppinglist)
  }
  return { refetch }
}
