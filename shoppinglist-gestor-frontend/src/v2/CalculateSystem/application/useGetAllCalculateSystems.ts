import { CalculateSystem } from "../domain/CalculateSystem";
import { getAllCalculateSystems } from "../infrastructure/useCases/getAllCalculateSystems";

export function useGetAllCalculateSystems() {
  async function refetch(): Promise<CalculateSystem[]> {
    return await getAllCalculateSystems();
  }
  return {
    refetch
  };
}
