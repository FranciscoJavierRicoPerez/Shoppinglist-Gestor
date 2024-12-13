// This interface represents the format of the object that is received from the server
export interface ResponseShoppinglist {
  id: number
  creationDate: string
  code: string
  closeDate: string | null
  totalPrice: number
  isActive: boolean
}
