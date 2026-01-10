import { updateShoppinglistTotalPrice } from "../infrastructure/useCases/updateShoppinglistTotalPrice"

export function useUpdateShoppinglistTotalPrice() {
    async function refetch(idShoppinglist: number) {
        await updateShoppinglistTotalPrice(idShoppinglist);
    }
    return { refetch }
}