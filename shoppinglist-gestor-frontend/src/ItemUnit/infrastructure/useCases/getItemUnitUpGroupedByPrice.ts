import axios from 'axios'
import type { ResponseGetItemUnitUpGroupedByPrice } from '../models/responses/ResponseGetItemUnitUpGroupedByPrice'
import responseGetItemUnitUpGroupedByPrice from '@/ItemUnit/infrastructure/mocks/responseGetItemUnitUpGroupedByPrice.json'
import { createItemUnitGroupedByPrice } from '../services/itemUnitService'
import type { ItemUnitUpGroupedByPrice } from '@/ItemUnit/domain/ItemUnitUpGroupedByPrice'
import { apiClient } from '@/Core/config/apiClient'
import { ITEM_UNIT_ENDPOINTS, SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'
async function getItemUnitUpGroupedByPrice(
  idShoppinglistItem: number,
): Promise<ItemUnitUpGroupedByPrice> {
  const response: ResponseGetItemUnitUpGroupedByPrice =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglistItem)
  return createItemUnitGroupedByPrice(response)
}
async function InMemory(): Promise<ResponseGetItemUnitUpGroupedByPrice> {
  return responseGetItemUnitUpGroupedByPrice as ResponseGetItemUnitUpGroupedByPrice
}

async function Api(idShoppinglistItem: number): Promise<ResponseGetItemUnitUpGroupedByPrice> {
  const response = await apiClient.get(
    SHOPPINGLIST_ITEMS_ENDPOINTS.ITEMS_UNITS_UP_GROUPED_BY_PRICE_V1(idShoppinglistItem),
  )
  return response.data
}

export { getItemUnitUpGroupedByPrice }
