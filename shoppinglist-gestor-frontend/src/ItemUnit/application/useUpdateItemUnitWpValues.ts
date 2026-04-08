import type { RequestUpdateItemUnitWpData } from '../infrastructure/models/request/RequestUpdateItemUnitWpData'
import { updateItemUnitWpValues } from '../infrastructure/useCases/updateItemUnitWpValues'

export function useUpdateItemUnitWpValues() {
  async function refetch(idItemUnitWp: number, data: RequestUpdateItemUnitWpData): Promise<void> {
    await updateItemUnitWpValues(idItemUnitWp, data)
  }
  return { refetch }
}
