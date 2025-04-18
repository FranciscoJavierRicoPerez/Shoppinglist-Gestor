export interface ShoppinglistDetails {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
  //items: ShoppinglistItem[]
}

export const defaultShoppinglistDetails = {
  id: -1,
  creationDate: '',
  code: '',
  closeDate: '',
  totalPrice: -1,
  isActive: false,
  //items: [],
}
