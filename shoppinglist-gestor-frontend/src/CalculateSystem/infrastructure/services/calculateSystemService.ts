import type { CalculateSystem } from '@/CalculateSystem/domain/CalculateSystem'
import type { ResponseGetAllCalculateSystems } from '../models/response/ResponseGetAllCalculteSystems'
import type { ResponseCalculateSystem } from '../models/response/ResponseCalculateSystem'

export function createCalculateSystemList(data: ResponseGetAllCalculateSystems): CalculateSystem[] {
  let result: CalculateSystem[] = []
  data.calculateSystemDTOList.forEach((element) => {
    result.push(createCalculateSystem(element))
  })
  return result
}

export function createCalculateSystem(data: ResponseCalculateSystem): CalculateSystem {
  return {
    id: data.id,
    code: data.code,
    name: data.name,
  }
}
