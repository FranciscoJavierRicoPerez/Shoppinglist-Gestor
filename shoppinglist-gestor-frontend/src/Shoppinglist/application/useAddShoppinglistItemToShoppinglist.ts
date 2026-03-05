import { addShoppinglistItemToShoppinglist } from '../infrastructure/useCases/addShoppinglistItemToShoppinglist'

export function useAddShoppinglistItemToShoppinglist() {
  async function refetch(idShoppinglist: number, idShoppinglistItem: number): Promise<void> {
    await addShoppinglistItemToShoppinglist(idShoppinglist, idShoppinglistItem)
  }
  return { refetch }
}
