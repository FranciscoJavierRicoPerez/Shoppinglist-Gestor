import { RequestProduct } from "@/Product/infrastructure/models/RequestProduct";

export interface ResquestNewShoppinglistItem {
    requestProduct: RequestProduct
}

export const defaultRequestNewShoppinglistItem = {
    requestProduct : {
        name: ''
    }
}