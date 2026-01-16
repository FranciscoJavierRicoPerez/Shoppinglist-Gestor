import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import type { ShoppinglistMetadata } from './ShoppinglistMetadata'

export interface ShoppinglistDetailsMetadata {
  shoppinglistMetadata: ShoppinglistMetadata
  items: ShoppinglistItemMetadata[]
}
