import { CalculateSystem } from "@/CalculateSystem/domain/CalculateSystem";
import { ResponseCalculateSystems } from "../models/ResponseCalculateSystems";
import axios from "axios";
import { createCalculateSystemArray } from "@/CalculateSystem/infrastructure/services/calculateSystemService";
import responseGetAllCalculateSystems from '@/CalculateSystem/infrastructure/mocks/responsegGetAllCalculateSystems.json'

async function getAllCalculateSystems(): Promise<CalculateSystem[]> {
  const response: ResponseCalculateSystems[] =
    import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory()
      : await Api();
  return createCalculateSystemArray(response);
}

async function Api(): Promise<ResponseCalculateSystems[]> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER + "api/calculateSystem/v1/all";
  const response = await axios.get(url);
  return response.data;
}

async function InMemory(): Promise<ResponseCalculateSystems[]> {
  return responseGetAllCalculateSystems as ResponseCalculateSystems[];
}

export { getAllCalculateSystems };
