import type { ResponseItemUnitUpMetadata } from './ResponseItemUnitUpMetadata'

export interface ResponseGetItemUnitUpGroupedByPrice {
  totalPrice: number
  itemsUpGrouped: ResponseItemUnitUpMetadata[]
}
