export interface Shoppinglist {
  id: number
  creationDate: String
  code: String
  closeDate: String | null
  totalPrice: number
  isActive: boolean
}

export const defaultShoppinglist = {
  id: -1,
  creationDate: '',
  code: '',
  closeDate: null,
  totalPrice: -1,
  isActive: false,
}
