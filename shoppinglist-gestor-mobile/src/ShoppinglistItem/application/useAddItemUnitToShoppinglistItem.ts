import { RequestAddUnitaryPriceItemUnit } from "../infrastructure/models/RequestAddUnitaryPriceItemUnit";
import { RequestAddWeightPriceItemUnit } from "../infrastructure/models/RequestAddWeightPriceItemUnit";
import { addItemUnitUnitaryPriceToShoppinglistItem } from "../infrastructure/useCases/addItemUnitUnitaryPriceToShoppinglistItem";
import { addItemUnitWeightPriceToShoppinglistItem } from "../infrastructure/useCases/addItemUnitWeightPriceToShoppinglistItem";

export function useAddItemUnitToShoppinglistItem() {
    async function refetch(dataUP: RequestAddUnitaryPriceItemUnit | null, dataWP: RequestAddWeightPriceItemUnit | null, isUnitaryPrice: boolean = true) {
        if(isUnitaryPrice) {
            return await addItemUnitUnitaryPriceToShoppinglistItem(dataUP);
        } else {
            return await addItemUnitWeightPriceToShoppinglistItem(dataWP);
        }
        
    }

    return {refetch}

}