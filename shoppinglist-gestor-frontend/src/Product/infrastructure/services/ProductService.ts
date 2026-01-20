import type { ProductsNames } from '@/Product/domain/ProductsNames'
import type { ResponseProductsNames } from '../models/response/ResponseProductsNames'

export function createProductsNames(data: ResponseProductsNames): ProductsNames {
  let names: string[] = []
  data.productsNames.forEach((element) => {
    names.push(element)
  })
  return {
    productsNames: names,
  }
}
