import type { Product } from '@/Product/domain/Product'

export interface ResponseShoppinglistItem {
  id: number
  assignationToListDate: string
  calculatedPrice: number
  quantity: number
  product: Product | null
}
