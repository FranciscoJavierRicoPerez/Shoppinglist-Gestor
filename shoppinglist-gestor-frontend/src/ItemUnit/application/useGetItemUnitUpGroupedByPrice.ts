import type { ItemUnitUpGroupedByPrice } from '../domain/ItemUnitUpGroupedByPrice'
import { getItemUnitUpGroupedByPrice } from '../infrastructure/useCases/getItemUnitUpGroupedByPrice'

export function useGetItemUnitUpGroupedByPrice() {
  async function refetch(idShoppinglistItem: number): Promise<ItemUnitUpGroupedByPrice> {
    return await getItemUnitUpGroupedByPrice(idShoppinglistItem)
  }

  return { refetch }
}
