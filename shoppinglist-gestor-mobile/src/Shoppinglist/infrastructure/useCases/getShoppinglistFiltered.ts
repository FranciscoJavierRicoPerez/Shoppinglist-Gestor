import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import responseGetFilteredShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetFilteredShoppinglist.json'
import { createShoppinglistArray } from '../services/ShoppinglistService'
import type { ShoppinglistFilter } from '@/Shoppinglist/domain/ShoppinglistFilter'
async function getShoppinglistFiltered(data: ShoppinglistFilter): Promise<Shoppinglist[]> {
  return createShoppinglistArray(await InMemory())
}

async function InMemory(): Promise<ResponseShoppinglist[]> {
  return responseGetFilteredShoppinglist as ResponseShoppinglist[]
}

export { getShoppinglistFiltered }
