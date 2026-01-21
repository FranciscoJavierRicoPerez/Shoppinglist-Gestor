import type { CreateShoppinglistItemData } from '../infrastructure/models/dto/CreateShoppinglistItemData'
import type { RequestCreateShoppinglistItemForm } from '../infrastructure/models/request/RequestCreateShoppinglistItemForm'
import { createShoppinglistItem } from '../infrastructure/useCases/useCreateShoppinglistItem'

export function useCreateShoppinglistItem() {
  async function refetch(
    data: RequestCreateShoppinglistItemForm,
  ): Promise<CreateShoppinglistItemData> {
    return await createShoppinglistItem(data)
  }
  return { refetch }
}
