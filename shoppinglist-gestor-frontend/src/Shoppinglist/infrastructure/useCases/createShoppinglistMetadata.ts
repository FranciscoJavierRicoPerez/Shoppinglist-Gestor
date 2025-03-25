import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import responseCreateShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseCreateShoppinglist.json'
import { createShoppinglist } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
async function createShoppinglistMetadata(): Promise<Shoppinglist> {
  return createShoppinglist(await InMemory())
}

async function InMemory(): Promise<ResponseShoppinglist> {
  return responseCreateShoppinglist as ResponseShoppinglist
}

export { createShoppinglistMetadata }
