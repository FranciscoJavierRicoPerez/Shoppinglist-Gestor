import type { Product } from "@/Product/domain/Product";
import type { ResponseProduct } from "../models/ResponseProduct";
import type { ResponseProductDetails } from "../models/ResponseProductDetails";
import type { ProductDetails } from "@/Product/domain/ProductDetails";

export function createProductArray(data: ResponseProduct[]): Product[] {
  let products: Product[] = [];
  if (data !== null) {
    data.forEach((element) => {
      products.push(createProduct(element));
    });
  }
  return products;
}

export function createProduct(data: ResponseProduct): Product {
  return {
    id: data.id,
    name: data.name,
  };
}

export function createProductDetails(
  data: ResponseProductDetails
): ProductDetails {
  return {
    id: data.id,
    name: data.name,
  };
}
