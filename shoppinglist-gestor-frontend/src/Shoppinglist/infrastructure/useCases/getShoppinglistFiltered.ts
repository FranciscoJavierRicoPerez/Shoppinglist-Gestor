import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import responseGetFilteredShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetFilteredShoppinglist.json'
import { createShoppinglistArray, createShoppinglistTable } from '../services/ShoppinglistService'
import type { ShoppinglistFilter } from '@/Shoppinglist/domain/ShoppinglistFilter'
import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import type { ResponseGetShoppinglistTableMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistTableMetadata'
import axios from 'axios'
async function getShoppinglistFiltered(data: ShoppinglistFilter): Promise<ShoppinglistTable> {
  const response: ResponseGetShoppinglistTableMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(data)
  return createShoppinglistTable(response)
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetFilteredShoppinglist as ResponseGetShoppinglistTableMetadata
}

async function Api(data: ShoppinglistFilter): Promise<ResponseGetShoppinglistTableMetadata> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    'api/shoppinglist/v1/filter?code=' +
    data.code +
    '?creationDate=' +
    data.creationDate +
    '?closeDate=' +
    data.closeDate +
    '?totalPrice=' +
    data.totalPrice +
    '?isActive=' +
    data.isActive

  const response = await axios.get(url)
  return response.data
}

export { getShoppinglistFiltered }
