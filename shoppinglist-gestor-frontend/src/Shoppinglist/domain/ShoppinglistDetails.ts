import type { Product } from '@/Product/domain/Product'

export interface ShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  products: Product[]
}

export const defaultShoppinglistDetails = {
  id: -1,
  creationDate: '',
  code: '',
  closeDate: '',
  totalPrice: -1,
  isActive: false,
  products: [],
}
