import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '../models/ResponseShoppinglist'
import responseGetShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetNoActiveShoppinglist.json'
import { createShoppinglistArray } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
async function getNoActiveShoppinglist(): Promise<Shoppinglist[]> {
  return createShoppinglistArray(await InMemory())
}

async function InMemory(): Promise<ResponseShoppinglist[]> {
  return responseGetShoppinglist as ResponseShoppinglist[]
}

export { getNoActiveShoppinglist }
