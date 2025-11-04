import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
import type { ShoppinglistItemMetadata } from '@/v2/ShoppinglistItem/domain/ShoppinglistItemMetadata'

export interface ResponseGetShoppinglistDetailsMetadata {
  shoppinglistMetadata: ShoppinglistMetadata
  items: ShoppinglistItemMetadata[]
}
