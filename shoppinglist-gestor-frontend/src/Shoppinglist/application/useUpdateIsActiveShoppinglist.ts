import { updateIsActiveShoppinglist } from '@/Shoppinglist/infrastructure/useCases/updateIsActiveShoppinglist'

export function useUpdateIsActiveShoppinglist() {
  async function refetch(idShoppinglist: number): Promise<boolean> {
    return await updateIsActiveShoppinglist(idShoppinglist)
  }

  return { refetch }
}
