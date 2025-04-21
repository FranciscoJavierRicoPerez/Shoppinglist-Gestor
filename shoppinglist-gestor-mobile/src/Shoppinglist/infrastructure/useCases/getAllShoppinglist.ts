import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '../models/ResponseShoppinglist'
import responseGetShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetAllShoppinglist.json'
import { createShoppinglistArray } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
async function getAllShoppinglist(): Promise<Shoppinglist[]> {
  return createShoppinglistArray(await InMemory())
}

async function InMemory(): Promise<ResponseShoppinglist[]> {
  return responseGetShoppinglist as ResponseShoppinglist[]
}

export { getAllShoppinglist }
