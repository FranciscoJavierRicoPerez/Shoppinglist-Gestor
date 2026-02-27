import type { ResponseGetShoppinglistTableMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistTableMetadata'
import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import responseGetShoppinglisTableMetadata from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistTableMetadata.json'
import { createShoppinglistTable } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_VIEW_ENDPOINTS } from '@/Core/config/config'

async function getShoppinglistTableMetadata(): Promise<ShoppinglistTable> {
  const response: ResponseGetShoppinglistTableMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createShoppinglistTable(response)
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetShoppinglisTableMetadata as ResponseGetShoppinglistTableMetadata
}

async function Api(): Promise<ResponseGetShoppinglistTableMetadata> {
  const response = await apiClient.get(SHOPPINGLIST_VIEW_ENDPOINTS.GET_ALL_V1())
  return response.data
}

export { getShoppinglistTableMetadata }
