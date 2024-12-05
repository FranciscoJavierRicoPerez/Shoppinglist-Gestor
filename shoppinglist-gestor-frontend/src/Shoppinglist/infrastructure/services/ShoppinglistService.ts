import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'

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
