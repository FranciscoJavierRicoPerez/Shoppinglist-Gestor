import { updateIsActiveShoppinglist } from '@/Shoppinglist/infrastructure/useCases/updateIsActiveShoppinglist'

export function useUpdateIsActiveShoppinglist() {
  async function refetch(id: number): Promise<boolean> {
    return updateIsActiveShoppinglist(id)
  }

  return { refetch }
}
