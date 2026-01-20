import type { ShoppinglistItem } from '@/ShoppinglistItem/domain/ShoppinglistItem'
import type { ResponseShoppinglistItem } from '../models/ResponseShoppinglistItem'
// import { createProduct } from '@/Product/infrastructure/services/ProductService'
import type { ResponseShoppinglistItemsMetadata } from '../models/ResponseShoppinglistIemsMetadata'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import type { ResponseShoppinglistMetadata } from '@/Shoppinglist/infrastructure/models/responses/ResponseShoppinglistMetadata'

/* export function createShoppinglistItemArray(data: ResponseShoppinglistItem[]): ShoppinglistItem[] {
  let shoppinglistItemArray: ShoppinglistItem[] = []
  data.forEach((element) => {
    shoppinglistItemArray.push(createShoppinglistItem(element))
  })
  return shoppinglistItemArray
}

export function createShoppinglistItem(data: ResponseShoppinglistItem): ShoppinglistItem {
  return {
    id: data.id,
    assignationToListDate: data.assignationToListDate,
    calculatedPrice: data.calculatedPrice,
    quantity: data.quantity,
    product: createProduct(data.product),
  }
} */

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
