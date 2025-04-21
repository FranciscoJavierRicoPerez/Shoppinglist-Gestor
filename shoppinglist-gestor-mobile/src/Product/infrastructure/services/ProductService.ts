import type { Product } from '@/Product/domain/Product'
import type { ResponseProduct } from '../models/ResponseProduct'
import type { ResponseProductDetails } from '../models/ResponseProductDetails'
import type { ProductDetails } from '@/Product/domain/ProductDetails'
//import { createProductUnitList } from '@/ProductUnit/infrastructure/services/ProductUnitService'

export function createProductArray(data: ResponseProduct[]): Product[] | null {
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
    price: data.price,
  }
}

export function createProductDetails(data: ResponseProductDetails): ProductDetails {
  return {
    id: data.id,
    name: data.name,
    quantity: data.quantity,
    price: data.price,
    //productUnitList: createProductUnitList(data.productUnitList),
  }
}
