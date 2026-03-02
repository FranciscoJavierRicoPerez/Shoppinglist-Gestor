import responseGetFilteredShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetFilteredShoppinglist.json'
import { createShoppinglistTable } from '../services/ShoppinglistService'
import type { ShoppinglistFilter } from '@/Shoppinglist/domain/ShoppinglistFilter'
import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import type { ResponseGetShoppinglistTableMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseGetShoppinglistTableMetadata'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'
async function getShoppinglistFiltered(data: ShoppinglistFilter): Promise<ShoppinglistTable> {
  const response: ResponseGetShoppinglistTableMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(data)
  return createShoppinglistTable(response)
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetFilteredShoppinglist as ResponseGetShoppinglistTableMetadata
}

async function Api(data: ShoppinglistFilter): Promise<ResponseGetShoppinglistTableMetadata> {
  const response = await apiClient.get(
    SHOPPINGLIST_ENDPOINTS.FILTER_V1(
      data.code,
      data.creationDate,
      data.closeDate,
      data.totalPrice,
      data.isActive,
    ),
  )
  return response.data
}

export { getShoppinglistFiltered }
