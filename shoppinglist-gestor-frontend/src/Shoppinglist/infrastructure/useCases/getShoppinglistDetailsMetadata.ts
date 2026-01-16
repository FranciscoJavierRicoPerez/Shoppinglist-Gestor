import type { ShoppinglistDetailsMetadata } from '@/Shoppinglist/domain/ShoppinglistDetailsMetadata'
import type { ResponseShoppinglistDetailsMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistDetailsMetadata'
import responseGetShoppinglistDetailsMetadata from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetailsMetadata.json'
import axios from 'axios'
import { createShoppinglistDetailsViewMetadata } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'

async function InMemory(): Promise<ResponseShoppinglistDetailsMetadata> {
  return responseGetShoppinglistDetailsMetadata as ResponseShoppinglistDetailsMetadata
}

async function Api(idShoppinglist: number): Promise<ResponseShoppinglistDetailsMetadata> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    '/api/shoppinglistview/v1/' +
    idShoppinglist +
    '/details'
  const response = await axios.get(url)
  return response.data
}

async function getShoppinglistDetailsMetadata(
  idShoppinglist: number,
): Promise<ShoppinglistDetailsMetadata> {
  const response: ResponseShoppinglistDetailsMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglist)
  return createShoppinglistDetailsViewMetadata(response)
}

export { getShoppinglistDetailsMetadata }
