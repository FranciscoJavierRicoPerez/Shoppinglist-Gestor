import { Product } from "@/Product/domain/Product";
import { ResponseProduct } from "@/Product/infrastructure/models/ResponseProduct";
import responseGetAllProducts from '@/Product/infrastructure/mocks/responseGetAllProducts.json'
import { createProductArray } from "../services/ProductService";
async function getAllProducts() : Promise<Product[]> {
    return createProductArray(await InMemory());
}
async function InMemory() : Promise<ResponseProduct[]>{
    return responseGetAllProducts as ResponseProduct[];
}

export { getAllProducts}