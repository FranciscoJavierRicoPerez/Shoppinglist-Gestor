import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { createShoppinglistMetadata } from '@/Shoppinglist/infrastructure/useCases/createShoppinglistMetadata'

export function useCreateShoppinglistMetadata() {
  async function refetch(): Promise<Shoppinglist> {
    return await createShoppinglistMetadata()
  }
  return { refetch }
}
