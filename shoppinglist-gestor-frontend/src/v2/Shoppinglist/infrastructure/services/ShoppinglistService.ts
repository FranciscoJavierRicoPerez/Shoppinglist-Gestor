import type { Shoppinglist } from '@/v2/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '@/v2/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import type { ResponseShoppinglistDetails } from '@/v2/Shoppinglist/infrastructure/models/ResponseShoppinglistDetails'
import type { ShoppinglistDetails } from '@/v2/Shoppinglist/domain/ShoppinglistDetails'
import { createShoppinglistItemArray } from '@/v2/ShoppinglistItem/infrastructure/services/shoppinglistItemService'
import type { ResponseGetShoppinglistDetailsMetadata } from '@/v2/Shoppinglist/infrastructure/models/ResponseGetShoppinglistDetailsMetadata'
import type { ShoppinglistViewDetails } from '@/v2/Shoppinglist/domain/ShoppinglistViewDetails'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
import type { ResponseGetShoppinglistTableMetadata } from '@/v2/Shoppinglist/infrastructure/models/ResponseGetShoppinglistTableMetadata'

export function createShoppinglistArray(data: ResponseShoppinglist[]): Shoppinglist[] {
  let shoppinglistArray: Shoppinglist[] = []
  data.forEach((element: ResponseShoppinglist) => {
    shoppinglistArray.push(createShoppinglist(element))
  })
  return shoppinglistArray
}

export function createShoppinglist(data: ResponseShoppinglist): Shoppinglist {
  return {
    id: data.id,
    creationDate: data.creationDate,
    code: data.code,
    closeDate: data.closeDate,
    totalPrice: data.totalPrice,
    isActive: data.isActive,
  }
}

export function createShoppinglistDetails(data: ResponseShoppinglistDetails): ShoppinglistDetails {
  return {
    id: data.id,
    creationDate: data.creationDate,
    code: data.code,
    closeDate: data.closeDate,
    totalPrice: data.totalPrice,
    isActive: data.isActive,
    items: data.items !== null ? createShoppinglistItemArray(data.items) : [],
  }
}

export function createShoppinglistDetailsViewMetadata(
  data: ResponseGetShoppinglistDetailsMetadata,
): ShoppinglistViewDetails {
  return {
    shoppinglistMetadata: data.shoppinglistMetadata,
    items: data.items,
  }
}

export function createShoppinglistMetadataList(
  data: ResponseGetShoppinglistTableMetadata,
): ShoppinglistMetadata[] {
  let response: ShoppinglistMetadata[] = []
  data.shoppinglistTableMetadataList.forEach((element) => {
    response.push({
      idShoppinglist: element.idShoppinglist,
      code: element.code,
      creationDate: element.creationDate,
      totalPrice: element.totalPrice,
      isActive: element.isActive,
    })
  })
  return response
}
