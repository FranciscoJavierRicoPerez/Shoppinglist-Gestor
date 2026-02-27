import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import type { ResponseShoppinglistDetails } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglistDetails'
import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import { createShoppinglistItemMetadataArray } from '@/ShoppinglistItem/infrastructure/services/shoppinglistItemService'
import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import type { ResponseGetShoppinglistTableMetadata } from '../models/responses/ResponseGetShoppinglistTableMetadata'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import type { ResponseShoppinglistMetadata } from '../models/responses/ResponseShoppinglistMetadata'
import type { ResponseShoppinglistDetailsMetadata } from '../models/responses/ResponseGetShoppinglistDetailsMetadata'
import type { ShoppinglistDetailsMetadata } from '@/Shoppinglist/domain/ShoppinglistDetailsMetadata'

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

/* export function createShoppinglistDetails(data: ResponseShoppinglistDetails): ShoppinglistDetails {
  return {
    id: data.id,
    creationDate: data.creationDate,
    code: data.code,
    closeDate: data.closeDate,
    totalPrice: data.totalPrice,
    isActive: data.isActive,
    items: createShoppinglistItemArray(data.items),
  }
} */

export function createShoppinglistTable(
  data: ResponseGetShoppinglistTableMetadata,
): ShoppinglistTable {
  let shoppinglistTable: ShoppinglistMetadata[] = []
  debugger
  console.log(data)
  data.shoppinglistTableMetadataList.forEach((element) => {
    shoppinglistTable.push(createShoppinglistMetadataService(element))
  })
  return {
    shoppinglistTable: shoppinglistTable,
  }
}

export function createShoppinglistMetadataService(
  data: ResponseShoppinglistMetadata,
): ShoppinglistMetadata {
  return {
    idShoppinglist: data.idShoppinglist,
    code: data.code,
    creationDate: data.creationDate,
    totalPrice: data.totalPrice,
    isActive: data.isActive,
    closeDate: '',
  }
}

export function createShoppinglistDetailsViewMetadata(
  data: ResponseShoppinglistDetailsMetadata,
): ShoppinglistDetailsMetadata {
  return {
    shoppinglistMetadata: {
      idShoppinglist: data.shoppinglistMetadata.idShoppinglist,
      code: data.shoppinglistMetadata.code,
      creationDate: data.shoppinglistMetadata.creationDate,
      totalPrice: data.shoppinglistMetadata.totalPrice,
      isActive: data.shoppinglistMetadata.isActive,
      closeDate: '',
    },
    items: createShoppinglistItemMetadataArray(data.items),
  }
}
