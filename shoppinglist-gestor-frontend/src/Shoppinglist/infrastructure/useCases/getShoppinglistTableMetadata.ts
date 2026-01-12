import type { ResponseGetShoppinglistTableMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistTableMetadata'
import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import responseGetShoppinglisTableMetadata from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistTableMetadata.json'
import { createShoppinglistTable } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import axios from 'axios'

async function getShoppinglistTableMetadata(): Promise<ShoppinglistTable> {
  const response: ResponseGetShoppinglistTableMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createShoppinglistTable(response)
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetShoppinglisTableMetadata as ResponseGetShoppinglistTableMetadata
}

async function Api(): Promise<ResponseGetShoppinglistTableMetadata> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + '/api/shoppinglistview/v1/all'
  const response = await axios.get(url)
  return response.data
}

export { getShoppinglistTableMetadata }
