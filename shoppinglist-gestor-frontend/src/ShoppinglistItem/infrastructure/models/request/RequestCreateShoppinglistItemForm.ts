import type { RequestCreateUnitData } from '@/ItemUnit/infrastructure/models/request/RequestCreateItemUnitData'
import type { RequestCreateProductInfo } from '@/Product/infrastructure/models/request/RequestCreateProductInfo'

export interface RequestCreateShoppinglistItemForm {
  productInfo: RequestCreateProductInfo
  selectedCalculateSystem: number
  createItemUnitData: RequestCreateUnitData | null // -> null ahora mismo para hacer pruebas
}
