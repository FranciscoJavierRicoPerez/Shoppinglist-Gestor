import type { ResponseProduct } from '@/Product/infrastructure/models/ResponseProduct'

export interface ResponseShoppinglistItem {
  id: number
  assignationToListDate: string
  calculatedPrice: number
  quantity: number
  product: ResponseProduct
}
