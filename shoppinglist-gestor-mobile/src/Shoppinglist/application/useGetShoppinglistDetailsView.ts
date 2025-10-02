import { ShoppinglistViewDetails } from "@/Shoppinglist/domain/ShoppinglistViewDetails";
import { getShoppinglistDetailsMetadata } from "../infrastructure/useCases/getShoppinglistDetailsMetadata";

export function useGetShoppinglistDetailsView() {
    async function refetch(idShoppinglist: number) : Promise<ShoppinglistViewDetails> {
        return await getShoppinglistDetailsMetadata(idShoppinglist)
    }

    return {
        refetch
    }

}