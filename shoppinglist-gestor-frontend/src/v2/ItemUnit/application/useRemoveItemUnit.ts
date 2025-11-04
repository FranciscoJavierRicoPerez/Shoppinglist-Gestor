import { removeItem } from "../infrastructure/useCases/removeItemUnit";

export function useRemoveItemUnit() {
  async function refetch(
    idItemUnit: number,
    idShoppinglistItem: number
  ): Promise<void> {
    await removeItem(idItemUnit, idShoppinglistItem);
  }
  return { refetch };
}
