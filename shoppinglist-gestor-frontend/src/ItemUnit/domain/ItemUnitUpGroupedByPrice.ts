import type { ItemUnitUpMetadata } from './ItemUnitUpMetadata'

export interface ItemUnitUpGroupedByPrice {
  totalPrice: number
  itemsUpGrouped: ItemUnitUpMetadata[]
}
