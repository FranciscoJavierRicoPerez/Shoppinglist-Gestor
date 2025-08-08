import { Product } from "@/Product/domain/Product";
import { ResponseProduct } from "@/Product/infrastructure/models/ResponseProduct";
import responseGetAllProducts from "@/Product/infrastructure/mocks/responseGetAllProducts.json";
import { createProductArray } from "../services/ProductService";
import axios from "axios";
async function getAllProducts(): Promise<Product[]> {
  const response: Product[] =
    import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory()
      : await Api();
  return createProductArray(response);
}

async function Api(): Promise<ResponseProduct[]> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + "api/product/v1/all";
  const response = await axios.get(url);
  return response.data;
}

async function InMemory(): Promise<ResponseProduct[]> {
  return responseGetAllProducts as ResponseProduct[];
}

export { getAllProducts };
