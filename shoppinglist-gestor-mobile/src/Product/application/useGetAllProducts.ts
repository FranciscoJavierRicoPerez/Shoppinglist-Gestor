import { Product } from "@/Product/domain/Product";
import { getAllProducts } from "@/Product/infrastructure/useCases/getAllProducts";

export function useGetAllProducts() {
    async function refetch() : Promise<Product[]> {
        return await getAllProducts();
    }
    return { refetch }
}