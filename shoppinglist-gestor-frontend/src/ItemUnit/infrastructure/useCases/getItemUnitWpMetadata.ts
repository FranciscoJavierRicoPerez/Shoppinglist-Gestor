import type { ItemUnitWpMetadata } from '@/ItemUnit/domain/ItemUnitWpMetadata'
import type { ResponseItemUnitWpMetadata } from '../models/responses/ResponseItemUnitWpMetadata'
import responseGetItemUnitWpMetadata from '@/ItemUnit/infrastructure/mocks/responseGetItemUnitWpMetadata.json'
import axios from 'axios'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'

async function getItemUnitWpMetadata(idShoppinglistItem: number): Promise<ItemUnitWpMetadata> {
  const response: ResponseItemUnitWpMetadata =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglistItem)
  return {
    priceKg: response.priceKg,
    weight: response.weight,
    calculatedPrice: response.calculatedPrice,
  }
}

async function InMemory(): Promise<ResponseItemUnitWpMetadata> {
  return responseGetItemUnitWpMetadata as ResponseItemUnitWpMetadata
}
async function Api(idShoppinglistItem: number): Promise<ResponseItemUnitWpMetadata> {
  const response = await apiClient.get(
    SHOPPINGLIST_ITEMS_ENDPOINTS.GET_ITEM_UNIT_WP_V1(idShoppinglistItem),
  )
  return response.data
}

export { getItemUnitWpMetadata }
