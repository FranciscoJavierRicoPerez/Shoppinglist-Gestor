import responseCreateShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseCreateShoppinglist.json'
import { createShoppinglistMetadataService } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import type { ResponseCreateShoppinglist } from '@/Shoppinglist/infrastructure/models/responses/ResponseCreateShoppinglist'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import axios from 'axios'
async function createShoppinglistMetadata(): Promise<ShoppinglistMetadata> {
  const response: ResponseCreateShoppinglist =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createShoppinglistMetadataService(response.shoppinglistCreated)
}

async function InMemory(): Promise<ResponseCreateShoppinglist> {
  return responseCreateShoppinglist as ResponseCreateShoppinglist
}

async function Api(): Promise<ResponseCreateShoppinglist> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v2/create'
  const response = await axios.post(url, {
    code: 'CODE',
    totalPrice: 0,
    isActive: true,
    infoBlock: false,
  })
  return response.data
}

export { createShoppinglistMetadata }
