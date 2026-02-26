import type { ShoppinglistDetailsMetadata } from '@/Shoppinglist/domain/ShoppinglistDetailsMetadata'
import type { ResponseShoppinglistDetailsMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistDetailsMetadata'
import responseGetShoppinglistDetailsMetadata from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetailsMetadata.json'
import { createShoppinglistDetailsViewMetadata } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_VIEW_ENDPOINTS } from '@/Core/config/config'

async function InMemory(): Promise<ResponseShoppinglistDetailsMetadata> {
  return responseGetShoppinglistDetailsMetadata as ResponseShoppinglistDetailsMetadata
}

async function Api(idShoppinglist: number): Promise<ResponseShoppinglistDetailsMetadata> {
  const response = await apiClient.get(SHOPPINGLIST_VIEW_ENDPOINTS.GET_DETAILS_V1(idShoppinglist))
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
