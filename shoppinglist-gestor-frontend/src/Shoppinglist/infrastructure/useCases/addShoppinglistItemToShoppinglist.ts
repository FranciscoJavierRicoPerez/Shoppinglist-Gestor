import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'

async function addShoppinglistItemToShoppinglist(
  idShoppinglist: number,
  isShoppinglistItem: number,
): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === 'LOCAL'
    ? await InMemory()
    : await Api(idShoppinglist, isShoppinglistItem)
}

async function InMemory(): Promise<void> {}

async function Api(idShoppinglist: number, idShoppinglistItem: number): Promise<void> {
  await apiClient.post(
    SHOPPINGLIST_ENDPOINTS.ADD_SHOPPINGLIST_ITEM_V1(idShoppinglist, idShoppinglistItem),
  )
}

export { addShoppinglistItemToShoppinglist }
