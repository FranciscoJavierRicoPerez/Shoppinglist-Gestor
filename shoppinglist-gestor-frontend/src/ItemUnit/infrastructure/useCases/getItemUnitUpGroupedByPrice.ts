import axios from 'axios'
import type { ResponseGetItemUnitUpGroupedByPrice } from '../models/responses/ResponseGetItemUnitUpGroupedByPrice'
import responseGetItemUnitUpGroupedByPrice from '@/ItemUnit/infrastructure/mocks/responseGetItemUnitUpGroupedByPrice.json'
import { createItemUnitGroupedByPrice } from '../services/itemUnitService'
import type { ItemUnitUpGroupedByPrice } from '@/ItemUnit/domain/ItemUnitUpGroupedByPrice'
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
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    'api/shoppinglistitem/v1/' +
    idShoppinglistItem +
    '/itemsUnitsUpGroupedByPrice'
  const response = await axios.get(url)
  return response.data
}

export { getItemUnitUpGroupedByPrice }
