import type { ShoppinglistItem } from '@/ShoppinglistItem/domain/ShoppinglistItem'
import responseGetFilteredShoppinglist from '@/ShoppinglistItem/infrastructure/mocks/responseGetAllShoppinglistItems.json'
import type { ResponseShoppinglistItem } from '@/ShoppinglistItem/infrastructure/models/ResponseShoppinglistItem'
async function getAllShoppinglistItems(): Promise<ShoppinglistItem[]> {
  return await InMemory()
}

async function InMemory(): Promise<ResponseShoppinglistItem[]> {
  return responseGetFilteredShoppinglist as ResponseShoppinglistItem[]
}

export { getAllShoppinglistItems }
