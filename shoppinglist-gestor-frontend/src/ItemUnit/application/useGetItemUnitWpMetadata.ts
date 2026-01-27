import type { ItemUnitWpMetadata } from '../domain/ItemUnitWpMetadata'
import { getItemUnitWpMetadata } from '../infrastructure/useCases/getItemUnitWpMetadata'

export function useGetItemUnitWpMetadata() {
  async function refetch(idShoppinglistItem: number): Promise<ItemUnitWpMetadata> {
    return await getItemUnitWpMetadata(idShoppinglistItem)
  }
  return { refetch }
}
