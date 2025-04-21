export interface Shoppinglist {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: Boolean | null
}

export const defaultShoppinglist = {
  id: -1,
  creationDate: '',
  code: '',
  closeDate: null,
  totalPrice: -1,
  isActive: false,
}
