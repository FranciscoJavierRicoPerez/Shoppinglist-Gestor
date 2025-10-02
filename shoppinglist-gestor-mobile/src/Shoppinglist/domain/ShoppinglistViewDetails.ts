import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { ShoppinglistMetadata } from "./ShoppinglistMetadata";

export interface ShoppinglistViewDetails {
    shoppinglistMetadata: ShoppinglistMetadata
    items: ShoppinglistItemMetadata[]
}