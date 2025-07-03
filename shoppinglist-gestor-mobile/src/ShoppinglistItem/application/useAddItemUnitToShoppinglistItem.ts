import { RequestAddUnitaryPriceItemUnit } from "../infrastructure/models/RequestAddUnitaryPriceItemUnit";
import { addItemUnitUnitaryPriceToShoppinglistItem } from "../infrastructure/useCases/addItemUnitUnitaryPriceToShoppinglistItem";

export function useAddItemUnitToShoppinglistItem() {
    async function refetch(data: RequestAddUnitaryPriceItemUnit, isUnitaryPrice: boolean = true) {
        return await addItemUnitUnitaryPriceToShoppinglistItem(data);
    }

    return {refetch}

}