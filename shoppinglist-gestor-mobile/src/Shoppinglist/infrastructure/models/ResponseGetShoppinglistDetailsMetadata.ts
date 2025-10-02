import { ShoppinglistMetadata } from "@/Shoppinglist/domain/ShoppinglistMetadata";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";

export interface ResponseGetShoppinglistDetailsMetadata {
    shoppinglistMetadata: ShoppinglistMetadata
    items: ShoppinglistItemMetadata[]
}