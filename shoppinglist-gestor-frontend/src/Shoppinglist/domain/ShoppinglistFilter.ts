export interface ShoppinglistFilter {
  creationDate: Date | null
  code: string
  closeDate: Date | null
  totalPrice: number | null
}

export const defaultShoppinglistFilter = {
  creationDate: null,
  code: '',
  closeDate: null,
  totalPrice: null,
}
