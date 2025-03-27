import { updateIsActiveShoppinglist } from '@/Shoppinglist/infrastructure/useCases/updateIsActiveShoppinglist'

export function useUpdateIsActiveShoppinglist() {
  async function refetch(): Promise<boolean> {
    return updateIsActiveShoppinglist()
  }

  return { refetch }
}
