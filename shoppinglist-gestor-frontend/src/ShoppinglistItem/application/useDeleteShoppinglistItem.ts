import type { DeleteShoppinglistItemData } from '../infrastructure/models/dto/DeleteShoppinglistItemData'
import { deleteShoppinglistItem } from '../infrastructure/useCases/deleteShoppinglistItem'

export function useDeleteShoppinglistItem() {
  async function refetch(idShoppinglistItem: number): Promise<DeleteShoppinglistItemData> {
    return await deleteShoppinglistItem(idShoppinglistItem)
  }

  return { refetch }
}
