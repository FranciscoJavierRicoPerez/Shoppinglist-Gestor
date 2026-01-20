import type { CalculateSystem } from '@/CalculateSystem/domain/CalculateSystem'
import type { ResponseGetAllCalculateSystems } from '../models/response/ResponseGetAllCalculteSystems'
import responseGetAllCalculateSystems from '@/CalculateSystem/infrastructure/mocks/responseGetAllCalculateSystems.json'
import axios from 'axios'
import { createCalculateSystemList } from '@/CalculateSystem/infrastructure/services/calculateSystemService'

async function getAllCalculateSystems(): Promise<CalculateSystem[]> {
  const response: ResponseGetAllCalculateSystems =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createCalculateSystemList(response)
}

async function InMemory(): Promise<ResponseGetAllCalculateSystems> {
  return responseGetAllCalculateSystems as ResponseGetAllCalculateSystems
}

async function Api(): Promise<ResponseGetAllCalculateSystems> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + '/api/calculateSystem/v1/all'
  const response = await axios.get(url)
  return response.data
}

export { getAllCalculateSystems }
