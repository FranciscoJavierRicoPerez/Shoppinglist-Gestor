import type { ItemUnitUpGroupedByPrice } from '@/ItemUnit/domain/ItemUnitUpGroupedByPrice'
import type { ResponseGetItemUnitUpGroupedByPrice } from '../models/responses/ResponseGetItemUnitUpGroupedByPrice'
import type { ResponseItemUnitUpMetadata } from '../models/responses/ResponseItemUnitUpMetadata'
import type { ItemUnitUpMetadata } from '@/ItemUnit/domain/ItemUnitUpMetadata'

export function createItemUnitGroupedByPrice(
  data: ResponseGetItemUnitUpGroupedByPrice,
): ItemUnitUpGroupedByPrice {
  return {
    totalPrice: data.totalPrice,
    itemsUpGrouped: createItemUnitUpMetadataList(data.itemsUpGrouped),
  }
}

export function createItemUnitUpMetadataList(
  data: ResponseItemUnitUpMetadata[],
): ItemUnitUpMetadata[] {
  let response: ItemUnitUpMetadata[] = []
  data.forEach((element) => {
    response.push({
      quantity: element.quantity,
      calculatedPrice: element.calculatedPrice,
      price: element.price,
    })
  })
  return response
}
