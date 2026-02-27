import responseCreateShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseCreateShoppinglist.json'
import { createShoppinglistMetadataService } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import type { ResponseCreateShoppinglist } from '@/Shoppinglist/infrastructure/models/responses/ResponseCreateShoppinglist'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import axios from 'axios'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'
async function createShoppinglistMetadata(): Promise<ShoppinglistMetadata> {
  const response: ResponseCreateShoppinglist =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createShoppinglistMetadataService(response.shoppinglistCreated)
}

async function InMemory(): Promise<ResponseCreateShoppinglist> {
  return responseCreateShoppinglist as ResponseCreateShoppinglist
}

async function Api(): Promise<ResponseCreateShoppinglist> {
  const response = await apiClient.post(SHOPPINGLIST_ENDPOINTS.CREATE_V2(), {
    code: 'CODE',
    totalPrice: 0,
    isActive: true,
    infoBlock: false,
  })
  return response.data
}

export { createShoppinglistMetadata }
