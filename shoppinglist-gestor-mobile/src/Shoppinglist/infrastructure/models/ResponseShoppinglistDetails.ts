import type { ResponseShoppinglistItem } from '@/ShoppinglistItem/infrastructure/models/ResponseShoppinglistItem'

export interface ResponseShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  items: ResponseShoppinglistItem[]
}
