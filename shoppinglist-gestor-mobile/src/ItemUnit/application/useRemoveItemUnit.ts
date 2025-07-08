import { removeItem } from "../infrastructure/useCases/removeItemUnit";

export function useRemoveItemUnit() {
  async function refetch(
    idItemUnit: number,
    idShoppinglistItem: number
  ): Promise<void> {
    removeItem(idItemUnit, idShoppinglistItem);
  }
  return { refetch };
}
