import type { ShoppinglistItem } from '@/ShoppinglistItem/domain/ShoppinglistItem'
import type { ResponseShoppinglistItem } from '../models/ResponseShoppinglistItem'
import { createProduct } from '@/Product/infrastructure/services/ProductService'

export function createShoppinglistItemArray(data: ResponseShoppinglistItem[]): ShoppinglistItem[] {
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
}
