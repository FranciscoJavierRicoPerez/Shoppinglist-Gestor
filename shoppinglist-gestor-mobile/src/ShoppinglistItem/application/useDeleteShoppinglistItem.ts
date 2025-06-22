import { ResponseDeleteShoppinglistItem } from "../infrastructure/models/ResponseDeleteShoppinglistItem";
import { deleteShoppinglistItem } from "../infrastructure/useCases/deleteShoppinglistItem";

export function useDeleteShoppinglistItem() {
    async function refetch(idItem: number) : Promise<ResponseDeleteShoppinglistItem>{
        return await deleteShoppinglistItem(idItem);
    }

    return { refetch }

}