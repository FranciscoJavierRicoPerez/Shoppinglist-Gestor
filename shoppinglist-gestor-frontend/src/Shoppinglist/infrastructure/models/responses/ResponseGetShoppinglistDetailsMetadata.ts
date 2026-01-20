import type { ResponseShoppinglistItemsMetadata } from '@/ShoppinglistItem/infrastructure/models/ResponseShoppinglistIemsMetadata'
import type { ResponseShoppinglistMetadata } from './ResponseShoppinglistMetadata'

export interface ResponseShoppinglistDetailsMetadata {
  shoppinglistMetadata: ResponseShoppinglistMetadata
  items: ResponseShoppinglistItemsMetadata[]
}
