import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import type { ResponseShoppinglistDetails } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglistDetails'
import responseGetShoppinglistDetails from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetails.json'

async function getShoppinglistDetails(): Promise<ShoppinglistDetails | null> {
  return null
}

async function InMemory(): Promise<ResponseShoppinglistDetails> {
  return responseGetShoppinglistDetails as ResponseShoppinglistDetails
}

export { getShoppinglistDetails }
