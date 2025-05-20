import { RequestProduct } from "@/Product/infrastructure/models/RequestProduct";

export interface ResquestNewShoppinglistItem {
  requestProduct: RequestProduct;
  calculateSystem: string;
  unitaryPrice: number | null;
}

export const defaultRequestNewShoppinglistItem = {
  requestProduct: {
    name: "",
  },
  calculateSystem: "",
  unitaryPrice: null,
};
