export interface ShoppinglistFilter {
  creationDate: Date | null
  code: string
  closeDate: Date | null
  totalPrice: number | null
  isActive: boolean | null // true || null => ACTIVAS # false => NO_ACTIVAS (ARCHIVADAS)
}

export const defaultShoppinglistFilter = {
  creationDate: null,
  code: '',
  closeDate: null,
  totalPrice: null,
  isActive: false,
}
