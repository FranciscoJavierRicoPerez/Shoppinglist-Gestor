import { addShoppinglistItemToShoppinglist } from "../infrastructure/useCases/addShoppinglistItemToShoppinglist"

export function useAddShoppinglistItemToShoppinglist() {
    async function refetch(idShoppinglist: number, idShoppinglistItem: number) {
        await addShoppinglistItemToShoppinglist(idShoppinglist, idShoppinglistItem)
    }
    return { refetch }
}