import { RequestProduct } from "@/Product/infrastructure/models/RequestProduct";

export interface ResquestNewShoppinglistItem {
  shoppinglistId: number;
  requestProduct: RequestProduct;
  calculateSystem: string;
  unitaryPrice: number | null;
}

export const defaultRequestNewShoppinglistItem = {
  shoppinglistId: -1,
  requestProduct: {
    productId: -1,
    name: "",
  },
  calculateSystem: "",
  unitaryPrice: null,
};
