import type { ItemUnitUpMetadata } from './ItemUnitUpMetadata'

export interface ItemUnitUpGroupedByPrice {
  // idItemUnit : number // -> Sería interesante para conocer el itemUnit a la hora de actualizaciones
  totalPrice: number
  itemsUpGrouped: ItemUnitUpMetadata[]
}
