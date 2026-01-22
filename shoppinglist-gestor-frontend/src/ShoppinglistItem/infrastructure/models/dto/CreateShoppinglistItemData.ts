import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'

export interface CreateShoppinglistItemData {
  created: boolean
  shoppinglistItemMetadata: ShoppinglistItemMetadata
}
