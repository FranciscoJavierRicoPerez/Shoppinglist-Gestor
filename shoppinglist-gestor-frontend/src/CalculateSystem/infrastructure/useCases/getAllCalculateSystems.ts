import type { CalculateSystem } from '@/CalculateSystem/domain/CalculateSystem'
import type { ResponseGetAllCalculateSystems } from '../models/response/ResponseGetAllCalculteSystems'
import responseGetAllCalculateSystems from '@/CalculateSystem/infrastructure/mocks/responseGetAllCalculateSystems.json'
import { createCalculateSystemList } from '@/CalculateSystem/infrastructure/services/calculateSystemService'
import { apiClient } from '@/Core/config/apiClient'
import { CALCULATE_SYSTEM_ENDPOINTS } from '@/Core/config/config'

async function getAllCalculateSystems(): Promise<CalculateSystem[]> {
  const response: ResponseGetAllCalculateSystems =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createCalculateSystemList(response)
}

async function InMemory(): Promise<ResponseGetAllCalculateSystems> {
  return responseGetAllCalculateSystems as ResponseGetAllCalculateSystems
}

async function Api(): Promise<ResponseGetAllCalculateSystems> {
  const response = await apiClient.get(CALCULATE_SYSTEM_ENDPOINTS.GET_ALL_V1())
  return response.data
}

export { getAllCalculateSystems }
