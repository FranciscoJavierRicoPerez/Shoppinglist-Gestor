import type { ResponseProduct } from '@/Product/infrastructure/models/ResponseProduct'

export interface ResponseShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  products: ResponseProduct[]
}
