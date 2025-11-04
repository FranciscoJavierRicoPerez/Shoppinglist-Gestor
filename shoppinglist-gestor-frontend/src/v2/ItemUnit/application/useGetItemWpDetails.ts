import { getItemWpDetails } from "../infrastructure/useCases/getItemWpDetails";

export function useGetItemWpDetails() {
  async function refetch(
    idShoppinglistItem: number
  ): Promise<ResponseItemUnitWpMetadata> {
    return await getItemWpDetails(idShoppinglistItem);
  }
  return {
    refetch,
  };
}
