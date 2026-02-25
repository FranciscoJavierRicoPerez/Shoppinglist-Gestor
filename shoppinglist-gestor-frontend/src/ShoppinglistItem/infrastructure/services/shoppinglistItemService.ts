import type { ResponseShoppinglistItemsMetadata } from '../models/ResponseShoppinglistIemsMetadata'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import type { ResponseCreateShoppinglistItem } from '../models/response/ResponseCreateShoppinglistItem'
import type { RequestCreateShoppinglistItemForm } from '../models/request/RequestCreateShoppinglistItemForm'

export function createShoppinglistItemMetadataArray(
  data: ResponseShoppinglistItemsMetadata[],
): ShoppinglistItemMetadata[] {
  let itemList: ShoppinglistItemMetadata[] = []
  data.forEach((element) => {
    itemList.push(createShoppinglistItemMetadata(element))
  })
  return itemList
}

export function createShoppinglistItemMetadata(
  data: ResponseShoppinglistItemsMetadata,
): ShoppinglistItemMetadata {
  return {
    idShoppinglistItem: data.id,
    calculatedPrice: data.calculatedPrice,
    calculateSystemCode: data.calculateSystemCode,
    assignationToLisDate: data.assignationToListDate,
    name: data.name,
  }
}

export function createShoppinglistItemMetadataFromCreateForm(
  data: ResponseCreateShoppinglistItem,
  form: RequestCreateShoppinglistItemForm,
): ShoppinglistItemMetadata {
  return {
    idShoppinglistItem: data.idShoppinglistItemCreated,
    name: form.productInfo.productName,
    assignationToLisDate: data.creationDate,
    calculateSystemCode: form.selectedCalculateSystem === 1 ? 'UP' : 'WP',
    calculatedPrice: data.shoppinglistItemCalculatedPrice,
  }
}
