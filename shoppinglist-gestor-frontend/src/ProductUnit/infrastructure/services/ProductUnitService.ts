import type { ProductUnit } from '@/ProductUnit/domain/ProductUnit'
import type { ResponseProductUnit } from '../models/ResponseProductUnit'

export function createProductUnitList(data: ResponseProductUnit[]): ProductUnit[] {
  let response: ProductUnit[] = []
  data.forEach((element) => {
    response.push(createProductUnit(element))
  })
  return response
}

export function createProductUnit(data: ResponseProductUnit): ProductUnit {
  return {
    id: data.id,
  }
}
