import type { RequestCreateUpItemUnitData } from './RequestCreateUpItemUnitData'
import type { RequestCreateWpItemUnitData } from './RequestCreateWpItemUnitData'

export interface RequestCreateUnitData {
  createItemUnit: boolean
  createUpItemUnitData: RequestCreateUpItemUnitData
  createWpItemUnitData: RequestCreateWpItemUnitData
}
