import type { ProductUnit } from '@/ProductUnit/domain/ProductUnit'

export interface ProductDetails {
  id: number
  name: string
  quantity: number
  price: number
  productUnitList: ProductUnit[]
}

export const defaultProductDetails = {
  id: -1,
  name: '',
  quantity: -1,
  price: -1,
  productUnitList: [],
}
