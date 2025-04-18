import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import type { ResponseShoppinglistDetails } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglistDetails'
import responseGetShoppinglistDetails from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetails.json'
import { createShoppinglistDetails } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'

async function getShoppinglistDetails(): Promise<ShoppinglistDetails> {
  return createShoppinglistDetails(await InMemory())
}

async function InMemory(): Promise<ResponseShoppinglistDetails> {
  return responseGetShoppinglistDetails as ResponseShoppinglistDetails
}

export { getShoppinglistDetails }
