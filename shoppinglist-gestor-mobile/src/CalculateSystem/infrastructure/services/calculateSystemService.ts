import { CalculateSystem } from "@/CalculateSystem/domain/CalculateSystem";
import { ResponseCalculateSystems } from "../models/ResponseCalculateSystems";

export function createCalculateSystemArray(
  data: ResponseCalculateSystems[]
): CalculateSystem[] {
  let response: CalculateSystem[] = [];
  if (data) {
    data.forEach((element) => {
      response.push(createCalculateSystem(element));
    });
  }
  return response;
}

export function createCalculateSystem(
  data: ResponseCalculateSystems
): CalculateSystem {
  return {
    id: data.id,
    name: data.name,
    code: data.code,
  };
}
