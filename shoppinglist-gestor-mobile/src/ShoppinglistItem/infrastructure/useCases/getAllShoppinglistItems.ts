import { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import responseGetFilteredShoppinglist from '@/ShoppinglistItem/infrastructure/mocks/responseGetAllShoppinglistItems.json'
import type { ResponseShoppinglistItemMetadata } from '@/ShoppinglistItem/infrastructure/models/ResponseShoppinglistItemMetadata'
async function getAllShoppinglistItems(): Promise<ShoppinglistItemMetadata[]> {
  return await InMemory()
}

async function InMemory(): Promise<ResponseShoppinglistItemMetadata[]> {
  return responseGetFilteredShoppinglist as ResponseShoppinglistItemMetadata[]
}

export { getAllShoppinglistItems }
