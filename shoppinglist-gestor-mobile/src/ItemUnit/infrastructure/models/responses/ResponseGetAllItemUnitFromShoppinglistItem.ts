import { ItemUnit } from "@/ItemUnit/domain/ItemUnit";

export interface ResponseGetAllItemUnitFromShoppinglistItem {
  itemUnitList: ItemUnit[];
  message: string;
}
