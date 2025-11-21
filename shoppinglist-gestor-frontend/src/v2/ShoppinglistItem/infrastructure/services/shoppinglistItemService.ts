import type { ResponseShoppinglistItemMetadata } from '@/v2/ShoppinglistItem/infrastructure/models/ResponseShoppinglistItemMetadata'
import type { ShoppinglistItemMetadata } from '@/v2/ShoppinglistItem/domain/ShoppinglistItemMetadata'

export function createShoppinglistItemArray(
  data: ResponseShoppinglistItemMetadata[],
): ShoppinglistItemMetadata[] {
  let shoppinglistItemArray: ShoppinglistItemMetadata[] = []
  data.forEach((element) => {
    shoppinglistItemArray.push(createShoppinglistItemMetadata(element))
  })
  return shoppinglistItemArray
}

export function createShoppinglistItemMetadata(
  data: ResponseShoppinglistItemMetadata,
): ShoppinglistItemMetadata {
  return {
    id: data.id,
    assignationToListDate: data.assignationToListDate,
    name: data.name,
    calculateSystemCode: data.calculateSystemCode,
    calculatedPrice: data.calculatedPrice,
  }
}
