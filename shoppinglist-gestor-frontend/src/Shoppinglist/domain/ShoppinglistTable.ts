import type { ShoppinglistMetadata } from './ShoppinglistMetadata'

export interface ShoppinglistTable {
  shoppinglistTable: ShoppinglistMetadata[]
}

export const defaultShoppinglistTable: ShoppinglistTable = {
  shoppinglistTable: [],
}
