export interface ResquestNewShoppinglistItem {
  productInfo: ProductInfo;
  selectedCalculateSystem: number;
  createItemUnitData: CreateItemUnitData;
}

export const defaultRequestNewShoppinglistItem = {
  productInfo: {
    alreadyExists: true,
    productName: "",
  },
  selectedCalculateSystem: -1,
  createItemUnitData: {
    createItemUnit: false,
    createUpItemUnitData: {
      quantity: -1,
      unitaryPrice: -1,
    },
    createWpItemUnitData: {
      priceKg: -1,
      weight: -1,
    },
  },
};

// ----------------
interface ProductInfo {
  alreadyExists: boolean;
  productName: string;
}

interface CreateItemUnitData {
  createItemUnit: boolean;
  createUpItemUnitData: CreateUpItemUnitData;
  createWpItemUnitData: CreateWpItemUnitData;
}

interface CreateUpItemUnitData {
  quantity: number;
  unitaryPrice: number;
}

interface CreateWpItemUnitData {
  priceKg: number;
  weight: number;
}
