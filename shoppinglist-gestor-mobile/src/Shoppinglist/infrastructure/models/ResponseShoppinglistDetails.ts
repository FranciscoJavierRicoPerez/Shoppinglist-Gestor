import { ResponseShoppinglistItemMetadata } from "@/ShoppinglistItem/infrastructure/models/ResponseShoppinglistItemMetadata"

export interface ResponseShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  items: ResponseShoppinglistItemMetadata[]
}
