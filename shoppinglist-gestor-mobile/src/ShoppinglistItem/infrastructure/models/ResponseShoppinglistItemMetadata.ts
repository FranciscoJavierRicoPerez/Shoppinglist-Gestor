import type { ResponseProduct } from '@/Product/infrastructure/models/ResponseProduct'

export interface ResponseShoppinglistItemMetadata {
  id: number
  assignationToListDate: string
  name: string
  calculateSystemCode: string
  calculatedPrice: number
}
