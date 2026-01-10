import { ShoppinglistItem } from "@/ShoppinglistItem/domain/ShoppinglistItem"
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata"

export interface ShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  items: ShoppinglistItemMetadata[]
}

export const defaultShoppinglistDetails = {
  id: -1,
  creationDate: '',
  code: '',
  closeDate: '',
  totalPrice: -1,
  isActive: false,
  items: [],
}
