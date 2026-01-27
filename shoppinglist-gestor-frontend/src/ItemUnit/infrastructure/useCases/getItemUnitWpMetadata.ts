import type { ItemUnitWpMetadata } from '@/ItemUnit/domain/ItemUnitWpMetadata'
import type { ResponseItemUnitWpMetadata } from '../models/responses/ResponseItemUnitWpMetadata'
import responseGetItemUnitWpMetadata from '@/ItemUnit/infrastructure/mocks/responseGetItemUnitWpMetadata.json'
import axios from 'axios'

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
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    'api/shoppinglistitem/v1/' +
    idShoppinglistItem +
    '/itemsUnistWpInfo'
  const response = await axios.get(url)
  return response.data
}

export { getItemUnitWpMetadata }
