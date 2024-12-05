// This interface represents the format of the object that is received from the server
export interface ResponseShoppinglist {
  id: number
  creationDate: String
  code: String
  closeDate: String | null
  totalPrice: number
  isActive: boolean
}
