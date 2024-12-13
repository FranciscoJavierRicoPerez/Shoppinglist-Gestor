import type { Product } from '@/Product/domain/Product'
import type { ResponseProduct } from '../models/ResponseProduct'

export function createProductArray(data: ResponseProduct[]): Product[] {
  let products: Product[] = []
  data.forEach((element) => {
    products.push(createProduct(element))
  })
  return products
}

export function createProduct(data: ResponseProduct): Product {
  return {
    id: data.id,
    name: data.name,
  }
}
