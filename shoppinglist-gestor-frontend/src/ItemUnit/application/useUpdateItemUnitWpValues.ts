import type { RequestUpdateItemUnitWpData } from '../infrastructure/models/request/RequestUpdateItemUnitWpData'

export function useUpdateItemUnitWpValues() {
  async function refetch(
    idItemUnit: number,
    idItemUnitWp: number,
    data: RequestUpdateItemUnitWpData,
  ): Promise<void> {}
  return { refetch }
}
